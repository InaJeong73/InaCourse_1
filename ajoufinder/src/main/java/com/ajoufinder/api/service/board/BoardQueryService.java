package com.ajoufinder.api.service.board;

import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;
import com.ajoufinder.api.controller.board.dto.response.BoardSimpleInfoResponseDto;
import com.ajoufinder.api.service.user.UserQueryService;
import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.board.BoardException;
import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardQueryService {
  private final BoardRepository boardRepository;
  private final UserQueryService userQueryService;

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
    return boardRepository.getAllLostBoards(pageable);
  }

  public Page<BoardSimpleInfoResponseDto> getFoundBoards(Pageable pageable) {
    return boardRepository.getAllFoundBoards(pageable);
  }

  public List<BoardSimpleInfoResponseDto> getBoardsByUserId(Long userId) {
    userQueryService.getUserByIdOrThrow(userId);
    return boardRepository.getBoardsByUserId(userId);
  }
}
