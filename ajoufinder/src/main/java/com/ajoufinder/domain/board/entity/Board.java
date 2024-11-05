package com.ajoufinder.domain.board.entity;

import com.ajoufinder.domain.TimeStamp;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.board.entity.constant.ItemType;
import com.ajoufinder.domain.location.entity.Location;
import com.ajoufinder.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Board extends TimeStamp {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="board_id")
  private Long boardId;

  @JsonBackReference(value = "user-boards")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name="user_id",nullable=false)
  private User user;

  @JsonBackReference(value = "location-boards")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="location_id")
  private Location location;

  @Column(nullable=false, length=100)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name="related_date",nullable=false)
  private LocalDateTime relatedDate;

  @Column(name="image_url",length=200)
  private String imageUrl;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private BoardStatus status;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private BoardCategory category;

  @Column(name="detailed_location", length=100)
  private String detailedLocation;

  @Enumerated(EnumType.STRING)
  @Column(name="item_type",nullable=false)
  private ItemType itemType;

  public void assignUser(User user){
    this.user = user;
    user.getBoards().add(this);
  }
  public void assignLocation(Location location){
    this.location = location;
    location.getBoards().add(this);
  }
}
