package com.ajoufinder.api.controller.board.dto.response;

import com.ajoufinder.domain.board.entity.Board;
import lombok.Builder;

@Builder
public record BoardCreateResponseDto(
        Long boardId,
        String message
){
public static BoardCreateResponseDto from(Board board) {
  return BoardCreateResponseDto.builder().boardId(board.getBoardId())
          .message("'"+board.getCategory().getText()+"' 게시글이 성공적으로 생성되었습니다.")
          .build();
}
}
