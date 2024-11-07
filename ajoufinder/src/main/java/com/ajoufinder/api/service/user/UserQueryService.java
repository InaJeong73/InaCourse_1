package com.ajoufinder.api.service.user;

import com.ajoufinder.common.exception.AjouFinderException;
import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.user.UserException;
import com.ajoufinder.domain.user.entity.User;
import com.ajoufinder.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {
  private final UserRepository userRepository;

  public User getUserByIdOrThrow(Long userId) {
    return userRepository.findById(userId).orElseThrow(
            ()->new UserException(ExceptionCode.NOT_FOUND_MEMBER));
  }

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public boolean existsByNickname(String nickname) {
    return userRepository.existsByNickname(nickname);
  }
}
