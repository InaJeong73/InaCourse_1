package com.ajoufinder.domain.board.repository.custom;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailTempDto;
import com.ajoufinder.api.controller.board.dto.response.BoardSimpleTempDto;
import com.ajoufinder.domain.board.entity.QBoard;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.location.entity.QLocation;
import com.ajoufinder.domain.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public BoardDetailTempDto findBoardWithUserAndLocation(Long boardId) {
    QBoard board = QBoard.board;
    QUser user = QUser.user;
    QLocation location = QLocation.location;

    return queryFactory
            .select(Projections.constructor(BoardDetailTempDto.class,
                    user.userId,
                    user.nickname,
                    location.locationId,
                    location.locationName,
                    board.title,
                    board.description,
                    board.relatedDate,
                    board.imageUrl,
                    board.status,
                    board.category,
                    board.detailedLocation,
                    board.itemType,
                    board.createdAt,
                    board.updatedAt
            ))
            .from(board)
            .join(board.user, user)
            .join(board.location, location)
            .where(board.boardId.eq(boardId))
            .fetchOne();
  }

  @Override
  public Page<BoardSimpleTempDto> getAllLostBoards(Pageable pageable) {
    return getBoardsByCategoryAndStatus(BoardCategory.LOST, pageable);
  }

  @Override
  public Page<BoardSimpleTempDto> getAllFoundBoards(Pageable pageable) {
    return getBoardsByCategoryAndStatus(BoardCategory.FIND, pageable);
  }

  @Override
  public List<BoardSimpleTempDto> getBoardsByUserId(Long userId) {
    QBoard board = QBoard.board;
    QUser user = QUser.user;
    QLocation location = QLocation.location;

    return queryFactory
            .select(Projections.constructor(BoardSimpleTempDto.class,
                    board.boardId,
                    user.userId,
                    user.nickname,
                    location.locationId,
                    location.locationName,
                    board.title,
                    board.relatedDate,
                    board.itemType,
                    board.status
            ))
            .from(board)
            .join(board.user, user)
            .join(board.location, location)
            .where (board.status.ne(BoardStatus.DELETED)
                    .and(user.userId.eq(userId)))
            .fetch();

  }

  @Override
  public Page<BoardSimpleTempDto> getBoardsByFilter(LocalDateTime start, LocalDateTime end, Long locationId, BoardStatus boardStatus, BoardCategory boardCategory, Pageable pageable) {
    QBoard board = QBoard.board;
    QUser user = QUser.user;
    QLocation location = QLocation.location;

    List<BoardSimpleTempDto> boards = queryFactory
            .select(Projections.constructor(BoardSimpleTempDto.class,
                    board.boardId,
                    user.userId,
                    user.nickname,
                    location.locationId,
                    location.locationName,
                    board.title,
                    board.relatedDate,
                    board.itemType,
                    board.status
            ))
            .from(board)
            .join(board.user, user)
            .join(board.location, location)
            .where(
                    relatedDateBetween(start, end),
                    locationIdEq(locationId),
                    boardStatusEq(boardStatus),
                    boardCategoryEq(boardCategory)
            )
            .fetch();

    long total = queryFactory
            .selectFrom(board)
            .leftJoin(board.location, location)
            .where(
                    relatedDateBetween(start, end),
                    locationIdEq(locationId),
                    boardStatusEq(boardStatus),
                    boardCategoryEq(boardCategory)
            )
            .fetchCount();

    return new PageImpl<>(boards, pageable, total);
  }

  private BooleanExpression boardStatusEq(BoardStatus boardStatus) {
    return boardStatus != null ? QBoard.board.status.eq(boardStatus) : null;
  }

  private BooleanExpression locationIdEq(Long locationId) {
    return locationId != null ? QLocation.location.locationId.eq(locationId) : null;
  }

  private BooleanExpression relatedDateBetween(LocalDateTime start, LocalDateTime end) {
    return (start!=null&&end!=null)?QBoard.board.relatedDate.between(start, end):null;
  }

  private BooleanExpression boardCategoryEq(BoardCategory boardCategory){
    return boardCategory!=null?QBoard.board.category.eq(boardCategory):null;
  }

  private Page<BoardSimpleTempDto> getBoardsByCategoryAndStatus(BoardCategory category, Pageable pageable) {
    QBoard board = QBoard.board;
    QUser user = QUser.user;
    QLocation location = QLocation.location;

    // 게시글 조회
    List<BoardSimpleTempDto> boards = queryFactory
            .select(Projections.constructor(BoardSimpleTempDto.class,
                    board.boardId,
                    user.userId,
                    user.nickname,
                    location.locationId,
                    location.locationName,
                    board.title,
                    board.relatedDate,
                    board.itemType,
                    board.status
            ))
            .from(board)
            .join(board.user, user)
            .join(board.location, location)
            .where(board.category.eq(category)
                    .and(board.status.ne(BoardStatus.DELETED)))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    // 총 게시물 수 계산
    long total = queryFactory
            .selectFrom(board)
            .where(board.category.eq(category)
                    .and(board.status.ne(BoardStatus.DELETED)))
            .fetchCount();

    // Page 객체로 반환
    return new PageImpl<>(boards, pageable, total);
  }
}

