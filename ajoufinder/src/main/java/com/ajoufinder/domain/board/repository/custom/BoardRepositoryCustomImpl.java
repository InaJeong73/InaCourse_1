package com.ajoufinder.domain.board.repository.custom;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;
import com.ajoufinder.api.controller.board.dto.response.BoardSimpleInfoResponseDto;
import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.board.entity.QBoard;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.location.entity.QLocation;
import com.ajoufinder.domain.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public BoardDetailInfoResponseDto findBoardWithUserAndLocation(Long boardId) {
    QBoard board = QBoard.board;
    QUser user = QUser.user;
    QLocation location = QLocation.location;

    return queryFactory
            .select(Projections.constructor(BoardDetailInfoResponseDto.class,
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
  public Page<BoardSimpleInfoResponseDto> getAllLostBoards(Pageable pageable) {
    QBoard board = QBoard.board;

    List<Board> boards = queryFactory
            .selectFrom(board)
            .where(board.category.eq(BoardCategory.LOST)
                    .and(board.status.ne(BoardStatus.DELETED)))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    List<BoardSimpleInfoResponseDto> dtoList = boards.stream()
            .map(boardEntity -> new BoardSimpleInfoResponseDto(
                    boardEntity.getUser().getUserId(),
                    boardEntity.getUser().getNickname(),
                    boardEntity.getLocation() != null ? boardEntity.getLocation().getLocationId() : null,
                    boardEntity.getLocation() != null ? boardEntity.getLocation().getLocationName() : null,
                    boardEntity.getTitle(),
                    boardEntity.getRelatedDate(),
                    boardEntity.getItemType()
            ))
            .collect(Collectors.toList());

    long total = queryFactory
            .selectFrom(board)
            .where(board.category.eq(BoardCategory.FIND)
                    .and(board.status.ne(BoardStatus.DELETED)))
            .fetchCount();

    return new PageImpl<>(dtoList, pageable, total);
  }


  public Page<BoardSimpleInfoResponseDto> getAllFoundBoards(Pageable pageable) {
    QBoard board = QBoard.board;

    List<Board> boards = queryFactory
            .selectFrom(board)
            .where(board.category.eq(BoardCategory.FIND)
                    .and(board.status.ne(BoardStatus.DELETED)))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    List<BoardSimpleInfoResponseDto> dtoList = boards.stream()
            .map(boardEntity -> new BoardSimpleInfoResponseDto(
                    boardEntity.getUser().getUserId(),
                    boardEntity.getUser().getNickname(),
                    boardEntity.getLocation() != null ? boardEntity.getLocation().getLocationId() : null,
                    boardEntity.getLocation() != null ? boardEntity.getLocation().getLocationName() : null,
                    boardEntity.getTitle(),
                    boardEntity.getRelatedDate(),
                    boardEntity.getItemType()
            ))
            .collect(Collectors.toList());

    long total = queryFactory
            .selectFrom(board)
            .where(board.category.eq(BoardCategory.FIND)
                    .and(board.status.ne(BoardStatus.DELETED)))
            .fetchCount();

    return new PageImpl<>(dtoList, pageable, total);
  }
}

