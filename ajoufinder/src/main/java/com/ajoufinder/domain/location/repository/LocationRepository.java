package com.ajoufinder.domain.location.repository;

import com.ajoufinder.domain.location.entity.Location;
import com.ajoufinder.domain.location.entity.constant.LocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
  boolean existsByLocationNameAndStatus(String locationName, LocationStatus status);
  List<Location> findByStatus(LocationStatus locationStatus);
}
