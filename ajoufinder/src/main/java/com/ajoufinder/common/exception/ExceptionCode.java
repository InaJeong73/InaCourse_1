package com.ajoufinder.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode{
  //User
  DUPLICATED_USER_EMAIL(409, "이미 존재하는 이메일입니다."),
  DUPLICATED_USER_NICKNAME(409, "이미 존재하는 닉네임입니다."),
  NOT_FOUND_MEMBER(404, "존재하지 않는 멤버입니다."),

  //Location
  NOT_FOUND_LOCATION(404, "존재하지 않는 위치입니다."),
  DUPLICATED_LOCATION(409, "이미 존재하는 위치입니다."),

  //Board
  NOT_FOUND_BOARD(404, "존재하지 않는 게시물입니다."),
  ALREADY_DELETED_BOARD(400,"이미 삭제된 게시물입니다."),
  INVALID_TIME_WITHOUT_DATE(400, "날짜가 없고 시간만 제공된 경우 조회할 수 없습니다.");

  private final int code;
  private final String message;
}
