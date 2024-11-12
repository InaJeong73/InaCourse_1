package com.ajoufinder.api.service.board;

import com.ajoufinder.api.controller.board.dto.request.BoardRequestDto;
import com.ajoufinder.api.service.location.LocationQueryService;
import com.ajoufinder.api.service.user.UserQueryService;
import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.board.BoardException;
import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.board.repository.BoardRepository;
import com.ajoufinder.domain.location.entity.Location;
import com.ajoufinder.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardCommandService {
  private final BoardRepository boardRepository;
  private final LocationQueryService locationQueryService;
  private final UserQueryService userQueryService;
  private final BoardQueryService boardQueryService;

  public Board updateBoard(Long boardId,BoardRequestDto requestDto) {
    Board board=boardQueryService.getBoardByIdOrThrow(boardId);
    if(board.isDeleted())throw new BoardException(ExceptionCode.ALREADY_DELETED_BOARD);
    Location location = checkLocation(requestDto.locationId());
    board.updateLocationInfo(location);
    board.updateBoardInfo(requestDto);
    boardRepository.save(board);
    return board;
  }

  public Board createBoard(BoardRequestDto requestDto, BoardCategory boardCategory) {
    User user=checkUser(requestDto.userId());
    Location location=checkLocation(requestDto.locationId());
    Board board=requestDto.toEntity(user, location, boardCategory);
    return boardRepository.save(board);
  }

  public Board createFoundBoard(BoardRequestDto requestDto) {
    return createBoard(requestDto, BoardCategory.FIND);
  }

  public Board createLostBoard(BoardRequestDto requestDto) {
    return createBoard(requestDto, BoardCategory.LOST);
  }

  public void deleteBoard(Long boardId) {
    Board board = boardQueryService.getBoardByIdOrThrow(boardId);
    board.changeStatus(BoardStatus.DELETED);
    boardRepository.save(board);
  }

  public User checkUser(Long userId) {
    return userQueryService.getUserByIdOrThrow(userId);
  }
  public Location checkLocation(Long locationId) {
    return locationQueryService.findLocationByIdOrThrow(locationId);
  }

  public Board updateBoardStatus(Long boardId, BoardStatus boardStatus) {
    Board board=boardQueryService.getBoardByIdOrThrow(boardId);
    board.updateBoardStatus(boardStatus);
    return boardRepository.save(board);
  }
}
