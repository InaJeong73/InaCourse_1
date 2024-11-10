package com.ajoufinder.domain.board.repository.custom;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;
import com.ajoufinder.api.controller.board.dto.response.BoardSimpleInfoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardRepositoryCustom {
  BoardDetailInfoResponseDto findBoardWithUserAndLocation(Long boardId);

  Page<BoardSimpleInfoResponseDto> getAllLostBoards(Pageable pageable);

  Page<BoardSimpleInfoResponseDto> getAllFoundBoards(Pageable pageable);
}
