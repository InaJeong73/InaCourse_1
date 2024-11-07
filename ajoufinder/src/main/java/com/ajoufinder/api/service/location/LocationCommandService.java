package com.ajoufinder.api.service.location;

import com.ajoufinder.api.controller.location.dto.request.LocationCreateRequestDto;
import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.location.LocationException;
import com.ajoufinder.domain.location.entity.Location;
import com.ajoufinder.domain.location.entity.constant.LocationStatus;
import com.ajoufinder.domain.location.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class LocationCommandService {
  private final LocationRepository locationRepository;
  private final LocationQueryService locationQueryService;

  public void createLocation(LocationCreateRequestDto requestDto) {
    String locationName=requestDto.locationName();
    if(locationRepository.existsByLocationName(locationName)){
      throw new LocationException(ExceptionCode.DUPLICATED_LOCATION);
    }
    locationRepository.save(requestDto.toEntity());
  }

  public void deleteLocation(Long locationId) {
    Location location=locationQueryService.findLocationByIdOrThrow(locationId);
    location.changeStatus(LocationStatus.DELETED);
    locationRepository.save(location);
  }
}
