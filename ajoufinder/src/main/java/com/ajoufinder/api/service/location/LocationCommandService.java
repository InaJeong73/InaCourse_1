package com.ajoufinder.api.service.location;

import com.ajoufinder.api.controller.location.dto.request.LocationRequestDto;
import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.location.LocationException;
import com.ajoufinder.domain.location.entity.Location;
import com.ajoufinder.domain.location.entity.constant.LocationStatus;
import com.ajoufinder.domain.location.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class LocationCommandService {
  private final LocationRepository locationRepository;
  private final LocationQueryService locationQueryService;

  public void createLocation(LocationRequestDto requestDto) {
    checkDuplicateLocationName(requestDto.locationName());
    locationRepository.save(requestDto.toEntity());
  }

  public void updateStatus(Long locationId, LocationStatus status) {
    Location location=locationQueryService.findLocationByIdOrThrow(locationId);
    location.changeStatus(status);
    locationRepository.save(location);
  }

  public void updateLocation(LocationRequestDto requestDto, Long locationId) {
    checkDuplicateLocationName(requestDto.locationName());
    Location location=locationQueryService.findLocationByIdOrThrow(locationId);
    location.updateFromDto(requestDto);
    locationRepository.save(location);
  }

  public void checkDuplicateLocationName(String locationName) {
    if(locationRepository.existsByLocationNameAndStatus(locationName, LocationStatus.ACTIVE)){
      throw new LocationException(ExceptionCode.DUPLICATED_LOCATION);
    }
  }
}
