package com.ajoufinder.api.service.location;

import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.location.LocationException;
import com.ajoufinder.domain.location.entity.Location;
import com.ajoufinder.domain.location.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class LocationQueryService {
  private final LocationRepository locationRepository;

  public Location findLocationByIdOrThrow(Long locationId) {
    return locationRepository.findById(locationId).orElseThrow(
            ()->new LocationException(ExceptionCode.NOT_FOUND_LOCATION));
  }
}
