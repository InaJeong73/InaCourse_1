package com.ajoufinder.domain.user.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
  ACTIVE("활성"),
  INACTIVE("비활성"),
  DELETED("삭제"),
  LOCKED("잠금");

  private final String text;
}
