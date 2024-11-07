package com.ajoufinder.api.controller.user.dto.response;

import com.ajoufinder.domain.user.entity.User;
import lombok.Builder;

@Builder
public record UserCreateResponseDto(
        Long userId,
        String message
) {
  public static UserCreateResponseDto from(User user) {
    return UserCreateResponseDto.builder().userId(user.getUserId())
            .message("회원가입 성공")
            .build();
  }
}
