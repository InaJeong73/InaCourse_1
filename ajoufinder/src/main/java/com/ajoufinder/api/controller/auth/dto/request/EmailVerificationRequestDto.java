package com.ajoufinder.api.controller.auth.dto.request;

import com.ajoufinder.common.valid.annotation.ValidAjouEmail;
import jakarta.validation.constraints.NotBlank;

public record EmailVerificationRequestDto(
        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @ValidAjouEmail
        String email
) {
}
