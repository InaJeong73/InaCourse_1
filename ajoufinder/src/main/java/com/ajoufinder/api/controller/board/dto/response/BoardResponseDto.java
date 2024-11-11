package com.ajoufinder.api.controller.board.dto.response;

import com.ajoufinder.domain.board.entity.Board;
import lombok.Builder;

@Builder
public record BoardResponseDto(
        Long boardId,
        String message
){
public static BoardResponseDto from(Long boardId,String message) {
  return BoardResponseDto.builder().boardId(boardId)
          .message(message)
          .build();
}
}
