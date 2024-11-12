package com.ajoufinder.domain.board.repository.custom;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;
import com.ajoufinder.api.controller.board.dto.response.BoardTempDto;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;


public interface BoardRepositoryCustom {
  BoardDetailInfoResponseDto findBoardWithUserAndLocation(Long boardId);

  Page<BoardTempDto> getAllLostBoards(Pageable pageable);

  Page<BoardTempDto> getAllFoundBoards(Pageable pageable);

  List<BoardTempDto> getBoardsByUserId(Long userId);

  Page<BoardTempDto> getBoardsByFilter(LocalDateTime start, LocalDateTime end, Long locationId, BoardStatus boardStatus, BoardCategory boardCategory,Pageable pageable);
}
