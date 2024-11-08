package com.ajoufinder.domain.board.repository.custom;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;
import com.ajoufinder.domain.board.entity.QBoard;
import com.ajoufinder.domain.location.entity.QLocation;
import com.ajoufinder.domain.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{
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
}
