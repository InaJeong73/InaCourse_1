package com.ajoufinder.common.valid.annotation;

import com.ajoufinder.common.valid.validator.AjouEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AjouEmailValidator.class) // 검증기를 지정
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAjouEmail {
  String message() default "유효한 Ajou University 이메일 주소여야 합니다."; // 기본 오류 메시지
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
