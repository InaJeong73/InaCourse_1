package com.ajoufinder.api.controller.location.dto.request;

import com.ajoufinder.domain.location.entity.Location;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LocationRequestDto(
        @NotBlank(message="위치/건물명은 필수 입력값입니다.")
        String locationName
){
  public Location toEntity() {
    return Location.builder().locationName(locationName).build();
  }
}
