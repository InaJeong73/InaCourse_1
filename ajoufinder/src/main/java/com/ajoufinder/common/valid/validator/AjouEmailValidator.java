package com.ajoufinder.common.valid.validator;

import com.ajoufinder.common.valid.annotation.ValidAjouEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AjouEmailValidator implements ConstraintValidator<ValidAjouEmail, String> {

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (email == null || email.isEmpty()) {
      return true; // @NotBlank 애너테이션으로 처리
    }
    return email.endsWith("@ajou.ac.kr"); // Ajou University 이메일 형식 검증
  }
}
