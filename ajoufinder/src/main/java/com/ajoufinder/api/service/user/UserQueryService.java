package com.ajoufinder.api.service.user;

import com.ajoufinder.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {
  private final UserRepository userRepository;


}
