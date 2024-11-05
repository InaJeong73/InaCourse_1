package com.ajoufinder.domain.board.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardStatus {
  IN_PROGRESS("진행중"),
  RESOLVED("해결"),
  DELETED("삭제");
  private final String text;
}
