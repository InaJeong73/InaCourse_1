package com.ajoufinder.api.controller.user.dto.request;

import com.ajoufinder.domain.user.entity.User;
import com.ajoufinder.domain.user.entity.constant.UserCategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserCreateRequestDto(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 8) // 비밀번호 최소 길이 예시
        String password,
        @NotBlank
        String nickname,
        @NotBlank
        String major,
        @NotBlank
        UserCategory category
) {
  public User toEntity(){
  return User.builder()
          .name(this.name)
          .email(this.email)
          .password(this.password)
          .nickname(this.nickname)
          .major(this.major)
          .category(this.category)
          .build();
  }
}

