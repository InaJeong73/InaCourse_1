package com.ajoufinder.domain.location.entity;

import com.ajoufinder.domain.TimeStamp;
import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.location.entity.constant.LocationStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Location extends TimeStamp {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="location_id")
  private Long locationId;

  @Column(name="location_name", length = 100)
  private String locationName;

  @Enumerated(EnumType.STRING)
  private LocationStatus status;

  @OneToMany(mappedBy="location",fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Board>boards=new ArrayList<>();

  @Builder
  public Location(String locationName) {
    this.locationName = locationName;
    this.status=LocationStatus.ACTIVE;
  }

  public void changeStatus(LocationStatus newStatus) {
    this.status=newStatus;
  }
}
