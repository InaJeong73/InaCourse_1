package com.ajoufinder.api.service.board;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;
import com.ajoufinder.api.controller.board.dto.response.BoardSimpleInfoResponseDto;
import com.ajoufinder.api.controller.board.dto.response.BoardTempDto;
import com.ajoufinder.api.service.location.LocationQueryService;
import com.ajoufinder.api.service.user.UserQueryService;
import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.board.BoardException;
import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BoardQueryService {
  private final BoardRepository boardRepository;
  private final UserQueryService userQueryService;
  private final LocationQueryService locationQueryService;

  public Board getBoardByIdOrThrow(Long boardId) {
    return boardRepository.findById(boardId).orElseThrow(()->new BoardException(ExceptionCode.NOT_FOUND_BOARD));
  }

  public BoardDetailInfoResponseDto getBoard(Long boardId){
    BoardDetailInfoResponseDto boardDetailInfoResponseDto=boardRepository.findBoardWithUserAndLocation(boardId);
    if(boardDetailInfoResponseDto==null){
      throw new BoardException(ExceptionCode.NOT_FOUND_BOARD);
    }
    return boardDetailInfoResponseDto;
  }

  public Page<BoardSimpleInfoResponseDto> getLostBoards(Pageable pageable) {
    Page<BoardTempDto>boardTempDtos= boardRepository.getAllLostBoards(pageable);
    return boardTempDtos.map(BoardSimpleInfoResponseDto::from);
  }

  public Page<BoardSimpleInfoResponseDto> getFoundBoards(Pageable pageable) {
    Page<BoardTempDto>boardTempDtos= boardRepository.getAllFoundBoards(pageable);
    return boardTempDtos.map(BoardSimpleInfoResponseDto::from);
  }

  public List<BoardSimpleInfoResponseDto> getBoardsByUserId(Long userId) {
    userQueryService.getUserByIdOrThrow(userId);
    List<BoardTempDto>boardTempDtos= boardRepository.getBoardsByUserId(userId);
    return boardTempDtos.stream().map(BoardSimpleInfoResponseDto::from)
            .collect(Collectors.toList());
  }

  public Page<BoardSimpleInfoResponseDto> getBoardsByFilter(
          LocalDate date,
          LocalTime time,
          Long locationId,
          BoardStatus boardStatus,
          BoardCategory boardCategory,
          Pageable pageable
  ) {
    LocalDateTime start = null;
    LocalDateTime end = null;

    if (locationId != null) {
      locationQueryService.findLocationByIdOrThrow(locationId);
    }

    if (date != null) {
      if (time != null) {
        start = date.atTime(time);
        end = start.plusHours(1);
      }else {
        start = date.atStartOfDay();
        end = date.plusDays(1).atStartOfDay();
      }
    } else if (time != null) {
      throw new BoardException(ExceptionCode.INVALID_TIME_WITHOUT_DATE);
    }

    Page<BoardTempDto> boardTempDtos= boardRepository.getBoardsByFilter(start, end, locationId, boardStatus, boardCategory, pageable);
    return boardTempDtos.map(BoardSimpleInfoResponseDto::from);
  }
}
