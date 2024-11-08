package com.ajoufinder.api.controller.location;

import com.ajoufinder.api.controller.location.dto.request.LocationRequestDto;
import com.ajoufinder.api.controller.location.dto.response.LocationResponseDto;
import com.ajoufinder.api.service.location.LocationCommandService;
import com.ajoufinder.api.service.location.LocationQueryService;
import com.ajoufinder.domain.location.entity.constant.LocationStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {
  private final LocationCommandService locationCommandService;
  private final LocationQueryService locationQueryService;

  @PostMapping
  public ResponseEntity<String> createLocation(@RequestBody @Valid LocationRequestDto requestDto) {
    locationCommandService.createLocation(requestDto);
    return new ResponseEntity<>("위치가 추가되었습니다.",HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<LocationResponseDto>> getAllLocations() {
    return new ResponseEntity<>(locationQueryService.findAllLocations(), HttpStatus.OK);
  }

  @DeleteMapping("/{locationId}")
  public ResponseEntity<Void> deleteLocation(@PathVariable Long locationId) {
    locationCommandService.updateStatus(locationId, LocationStatus.DELETED);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PatchMapping("/{locationId}/activate")
  public ResponseEntity<String> activateLocation(@PathVariable Long locationId) {
    locationCommandService.updateStatus(locationId, LocationStatus.ACTIVE);
    return new ResponseEntity<>("위치가 복원되었습니다.",HttpStatus.OK);
  }

  @PatchMapping("/{locationId}/update")
  public ResponseEntity<String>updateLocation(@RequestBody LocationRequestDto requestDto, @PathVariable Long locationId) {
    locationCommandService.updateLocation(requestDto, locationId);
    return new ResponseEntity<>("위치 정보가 수정되었습니다.",HttpStatus.OK);
  }
}
