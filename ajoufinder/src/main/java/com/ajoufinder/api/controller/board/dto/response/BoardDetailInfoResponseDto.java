package com.ajoufinder.api.controller.board.dto.response;

import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.board.entity.constant.ItemType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardDetailInfoResponseDto(
        Long userId,
        String nickname,
        Long locationId,
        String locationName,
        String title,
        String description,
        LocalDateTime relatedDate,
        String imageUrl,
        String status,
        String category,
        String detailedLocation,
        String itemType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
  public static BoardDetailInfoResponseDto from(BoardDetailTempDto dto) {
    return BoardDetailInfoResponseDto.builder()
            .userId(dto.userId())
            .nickname(dto.nickname())
            .locationId(dto.locationId())
            .locationName(dto.locationName())
            .title(dto.title())
            .relatedDate(dto.relatedDate())
            .imageUrl(dto.imageUrl())
            .itemType(dto.itemType().getText())
            .status(dto.status().getText())
            .category(dto.category().getText())
            .detailedLocation(dto.detailedLocation())
            .itemType(dto.itemType().getText())
            .createdAt(dto.createdAt())
            .updatedAt(dto.updatedAt())
            .build();
  }
}
