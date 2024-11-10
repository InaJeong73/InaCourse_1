package com.ajoufinder.api.controller.board.dto.response;

import com.ajoufinder.domain.board.entity.constant.ItemType;

import java.time.LocalDateTime;

public record BoardSimpleInfoResponseDto(
        Long userId,
        String nickname,
        Long locationId,
        String locationName,
        String title,
        LocalDateTime relatedDate,
        ItemType itemType
) {

}