package com.ajoufinder.api.controller.location.dto.response;

import com.ajoufinder.domain.location.entity.Location;
import lombok.Builder;

@Builder
public record LocationResponseDto(
        Long locationId,
        String locationName
) {
  public static LocationResponseDto from(Location location) {
    return LocationResponseDto.builder().locationId(location.getLocationId())
            .locationName(location.getLocationName()).build();
  }
}
