package com.ajoufinder.domain.user.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserCategory {
  UNDERGRADUATE("재학생"),
  LEAVE_OF_ABSENCE("휴학생"),
  GRADUATE("졸업생"),
  FACULTY("교직원"),
  STAFF("직원"),
  EXCHANGE_STUDENT("교환학생");

  private final String text;
}
