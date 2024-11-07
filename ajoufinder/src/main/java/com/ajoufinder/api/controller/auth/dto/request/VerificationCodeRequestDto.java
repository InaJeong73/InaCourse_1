package com.ajoufinder.api.controller.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record VerificationCodeRequestDto(
        @NotBlank
        String code
){
}
