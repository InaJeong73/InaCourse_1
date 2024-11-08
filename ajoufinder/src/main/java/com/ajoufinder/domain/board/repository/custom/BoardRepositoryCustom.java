package com.ajoufinder.domain.board.repository.custom;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;


public interface BoardRepositoryCustom {
  BoardDetailInfoResponseDto findBoardWithUserAndLocation(Long boardId);
}
