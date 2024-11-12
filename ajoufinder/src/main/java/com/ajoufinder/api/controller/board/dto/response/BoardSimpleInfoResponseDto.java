package com.ajoufinder.api.controller.board.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardSimpleInfoResponseDto(
        Long boardId,
        Long userId,
        String nickname,
        Long locationId,
        String locationName,
        String title,
        LocalDateTime relatedDate,
        String itemType,
        String boardStatus
) {
  public static BoardSimpleInfoResponseDto from(BoardTempDto dto) {
    return BoardSimpleInfoResponseDto.builder().boardId(dto.boardId())
            .userId(dto.userId())
            .nickname(dto.nickname())
            .locationId(dto.locationId())
            .locationName(dto.locationName())
            .title(dto.title())
            .relatedDate(dto.relatedDate())
            .itemType(dto.itemType().getText())
            .boardStatus(dto.boardStatus().getText())
            .build();
  }
}
