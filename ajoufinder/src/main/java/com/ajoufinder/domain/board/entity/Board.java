package com.ajoufinder.domain.board.entity;

import com.ajoufinder.api.controller.board.dto.request.BoardRequestDto;
import com.ajoufinder.domain.TimeStamp;
import com.ajoufinder.domain.board.entity.constant.BoardCategory;
import com.ajoufinder.domain.board.entity.constant.BoardStatus;
import com.ajoufinder.domain.board.entity.constant.ItemType;
import com.ajoufinder.domain.location.entity.Location;
import com.ajoufinder.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

  @Builder
  public Board(User user,Location location,String title,String description,LocalDateTime relatedDate,String imageUrl,BoardStatus status,BoardCategory category,String detailedLocation,ItemType itemType){
    this.user = user;
    this.location = location;
    this.title = title;
    this.description = description;
    this.relatedDate = relatedDate;
    this.imageUrl = imageUrl;
    this.status = status;
    this.category = category;
    this.detailedLocation = detailedLocation;
    this.itemType = itemType;
  }

  public void assignUser(User user){
    this.user = user;
    user.getBoards().add(this);
  }
  public void assignLocation(Location location){
    this.location = location;
    location.getBoards().add(this);
  }

  public void changeStatus(BoardStatus status){
    this.status = status;
  }

  public void updateBoardInfo(BoardRequestDto requestDto){
    if(!itemType.equals(requestDto.itemType())){
      itemType=requestDto.itemType();
    }
    detailedLocation=requestDto.detailedLocation();
    if(!title.equals(requestDto.title())){
      title=requestDto.title();
    }
    if(!description.equals(requestDto.description())){
      description=requestDto.description();
    }
    if(!relatedDate.equals(requestDto.relatedDate())){
      relatedDate=requestDto.relatedDate();
    }
    imageUrl=requestDto.imageUrl();
  }

  public void updateLocationInfo(Location location){
    if(!location.equals(this.location)){
      this.location = location;
    }
  }
  public boolean isDeleted(){
    return this.status == BoardStatus.DELETED;
  }

  public void updateBoardStatus(BoardStatus boardStatus){
    this.status = boardStatus;
  }
}
