package com.ajoufinder.api.service.board;

import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.board.BoardException;
import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardQueryService {
  private final BoardRepository boardRepository;

  public Board getBoardByIdOrThrow(Long boardId) {
    return boardRepository.findById(boardId).orElseThrow(()->new BoardException(ExceptionCode.NOT_FOUND_BOARD));
  }
}
