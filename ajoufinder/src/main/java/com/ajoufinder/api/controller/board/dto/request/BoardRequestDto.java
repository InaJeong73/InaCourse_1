package com.ajoufinder.api.controller.board.dto.request;

import com.ajoufinder.common.valid.annotation.ValidEnum;
import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.board.entity.constant.ItemType;
import com.ajoufinder.domain.location.entity.Location;
import com.ajoufinder.domain.user.entity.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BoardRequestDto(
        @NotNull(message="사용자는 필수 입력값입니다.")
        Long userId,

        @NotNull(message="위치는 필수 입력값입니다.")
        Long locationId,

        @NotNull(message="아이템 종류는 필수 입력값입니다.")
        @ValidEnum(enumClass = ItemType.class, message = "유효하지 않은 아이템 종류입니다.")
        ItemType itemType,

        @Size(max = 100, message = "상세 위치는 최대 100자까지 허용됩니다.")
        String detailedLocation,

        @NotBlank(message="제목은 필수 입력값입니다.")
        @Size(max = 255, message = "제목은 최대 255자까지 허용됩니다.")
        String title,

        @NotBlank(message="내용은 필수 입력값입니다.")
        String description,

        @NotNull(message="습득 및 분실 날짜는 필수 입력값입니다.")
        LocalDateTime relatedDate,

        @Size(max = 255, message = "상세 위치는 최대 255자까지 허용됩니다.")
        String imageUrl
){
  public Board toEntity(User user, Location location,BoardCategory category) {
    return Board.builder().user(user)
            .location(location)
            .detailedLocation(detailedLocation)
            .itemType(itemType)
            .title(title)
            .description(description)
            .relatedDate(relatedDate)
            .imageUrl(imageUrl)
            .category(category)
            .status(BoardStatus.IN_PROGRESS)
            .build();
  }
}
