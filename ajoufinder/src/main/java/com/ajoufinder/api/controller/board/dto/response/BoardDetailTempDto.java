package com.ajoufinder.api.controller.board.dto.response;

import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.board.entity.constant.ItemType;
import java.time.LocalDateTime;


public record BoardDetailTempDto(
        Long userId,
        String nickname,
        Long locationId,
        String locationName,
        String title,
        String description,
        LocalDateTime relatedDate,
        String imageUrl,
        BoardStatus status,
        BoardCategory category,
        String detailedLocation,
        ItemType itemType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){
}
