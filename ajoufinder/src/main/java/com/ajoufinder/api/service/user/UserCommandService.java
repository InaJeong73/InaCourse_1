package com.ajoufinder.api.service.user;

import com.ajoufinder.api.controller.user.dto.request.UserCreateRequestDto;
import com.ajoufinder.common.exception.ExceptionCode;
import com.ajoufinder.common.exception.user.UserException;
import com.ajoufinder.domain.user.entity.User;
import com.ajoufinder.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserQueryService userQueryService;

  public User createUser(UserCreateRequestDto dto) {
    if(userQueryService.existsByEmail(dto.email())){
      throw new UserException(ExceptionCode.DUPLICATED_USER_EMAIL);
    }
    User user=dto.toEntity();
    user.passwordEncode(passwordEncoder);
    userRepository.save(user);
    return user;
  }
}