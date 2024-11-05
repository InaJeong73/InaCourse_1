package com.ajoufinder.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode{
  //User
  DUPLICATED_USER_EMAIL(409, "이미 존재하는 이메일입니다."),
  DUPLICATED_USER_NICKNAME(409, "이미 존재하는 닉네임입니다.");
  private final int code;
  private final String message;
}
