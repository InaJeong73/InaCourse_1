package com.ajoufinder.domain.location.entity;

import com.ajoufinder.domain.TimeStamp;
import com.ajoufinder.domain.board.entity.Board;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Location extends TimeStamp {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="location_id")
  private Long locationId;

  @Column(name="location_name", length = 100)
  private String locationName;

  @OneToMany(mappedBy="location",fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Board>boards=new ArrayList<>();

}
