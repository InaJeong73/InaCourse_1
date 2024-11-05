package com.ajoufinder.common.exception;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
