package com.ajoufinder.api.service.board;

import com.ajoufinder.api.controller.board.dto.request.BoardCreateRequestDto;
import com.ajoufinder.api.service.location.LocationQueryService;
import com.ajoufinder.api.service.user.UserQueryService;
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

  public Board createBoard(BoardCreateRequestDto requestDto,BoardCategory boardCategory) {
    Long userId=requestDto.userId();
    User user=userQueryService.getUserByIdOrThrow(userId);
    Long locationId=requestDto.locationId();
    Location location=locationQueryService.findLocationByIdOrThrow(locationId);
    Board board=requestDto.toEntity(user, location, boardCategory);
    return boardRepository.save(board);
  }

  public Board createFoundBoard(BoardCreateRequestDto requestDto) {
    return createBoard(requestDto, BoardCategory.FIND);
  }

  public Board createLostBoard(BoardCreateRequestDto requestDto) {
    return createBoard(requestDto, BoardCategory.LOST);
  }

  public void deleteBoard(Long boardId) {
    Board board = boardQueryService.getBoardByIdOrThrow(boardId);
    board.changeStatus(BoardStatus.DELETED);
    boardRepository.save(board);
  }
}
