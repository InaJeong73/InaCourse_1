package com.ajoufinder.api.controller.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserLoginRequestDto(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
