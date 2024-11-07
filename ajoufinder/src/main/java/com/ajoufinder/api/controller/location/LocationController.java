package com.ajoufinder.api.controller.location;

import com.ajoufinder.api.controller.location.dto.request.LocationCreateRequestDto;
import com.ajoufinder.api.service.location.LocationCommandService;
import com.ajoufinder.api.service.location.LocationQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {
  private final LocationCommandService locationCommandService;
  private final LocationQueryService locationQueryService;

  @PostMapping
  public ResponseEntity<String> createLocation(@RequestBody @Valid LocationCreateRequestDto requestDto) {
    locationCommandService.createLocation(requestDto);
    return new ResponseEntity<>("새로운 위치가 성공적으로 추가되었습니다.",HttpStatus.CREATED);
  }

  @DeleteMapping("/{locationId}")
  public ResponseEntity<Void> deleteLocation(@PathVariable Long locationId) {
    locationCommandService.deleteLocation(locationId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
