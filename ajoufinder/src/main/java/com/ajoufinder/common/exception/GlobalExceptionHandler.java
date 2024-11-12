package com.ajoufinder.common.exception;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(AjouFinderException.class)
  public ResponseEntity<ExceptionResponse>handleBadRequestException(AjouFinderException e){
    log.warn(e.getMessage());
    return ResponseEntity
            .status(e.getExceptionCode().getCode())
            .body(new ExceptionResponse(e.getExceptionCode().getCode(), e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
    String message = "입력된 값의 형식이 잘못되었습니다. 날짜는 yyyy-MM-dd 형식, 시간은 HH:mm 형식이어야 합니다.";
    return ResponseEntity.badRequest().body(message);
  }
}
