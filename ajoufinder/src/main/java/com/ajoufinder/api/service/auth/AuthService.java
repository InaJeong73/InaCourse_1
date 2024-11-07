package com.ajoufinder.api.service.auth;

import com.ajoufinder.api.service.user.UserQueryService;
import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.user.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserQueryService userQueryService;
  public void sendVerificationCode(String email) {
    if(userQueryService.existsByEmail(email)){
      throw new UserException(ExceptionCode.DUPLICATED_USER_EMAIL);
    }
  }

  public void verifyNickname(String nickname) {
    if(userQueryService.existsByNickname(nickname)){
      throw new UserException(ExceptionCode.DUPLICATED_USER_NICKNAME);
    }
  }


}
