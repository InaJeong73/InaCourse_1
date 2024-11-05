package com.ajoufinder.domain.board.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardCategory {
  FIND("주웠어요"),
  LOST("잃었어요");
  private final String text;
}
