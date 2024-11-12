package com.ajoufinder.domain.board.repository.custom;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailTempDto;
import com.ajoufinder.api.controller.board.dto.response.BoardSimpleTempDto;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;


public interface BoardRepositoryCustom {
  BoardDetailTempDto findBoardWithUserAndLocation(Long boardId);

  Page<BoardSimpleTempDto> getAllLostBoards(Pageable pageable);

  Page<BoardSimpleTempDto> getAllFoundBoards(Pageable pageable);

  List<BoardSimpleTempDto> getBoardsByUserId(Long userId);

  Page<BoardSimpleTempDto> getBoardsByFilter(LocalDateTime start, LocalDateTime end, Long locationId, BoardStatus boardStatus, BoardCategory boardCategory, Pageable pageable);
}
