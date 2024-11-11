package com.ajoufinder.domain.board.repository.custom;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;
import com.ajoufinder.api.controller.board.dto.response.BoardSimpleInfoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BoardRepositoryCustom {
  BoardDetailInfoResponseDto findBoardWithUserAndLocation(Long boardId);

  Page<BoardSimpleInfoResponseDto> getAllLostBoards(Pageable pageable);

  Page<BoardSimpleInfoResponseDto> getAllFoundBoards(Pageable pageable);

  List<BoardSimpleInfoResponseDto> getBoardsByUserId(Long userId);
}
