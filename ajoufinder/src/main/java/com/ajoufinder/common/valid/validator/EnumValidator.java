package com.ajoufinder.common.valid.validator;

import com.ajoufinder.common.valid.annotation.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum> {
  private Enum<?>[] enumConstants;

  @Override
  public void initialize(ValidEnum constraintAnnotation) {
    this.enumConstants = constraintAnnotation.enumClass().getEnumConstants();
  }

  @Override
  public boolean isValid(Enum value, ConstraintValidatorContext context) {
    if (value == null) {
     return true;
    }
    for (Enum<?> enumValue : enumConstants) {
      if (enumValue.name().equals(value.name())) {
        return true;
      }
    }
    return false;
  }
}
