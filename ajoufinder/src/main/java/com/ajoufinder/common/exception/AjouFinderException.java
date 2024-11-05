package com.ajoufinder.common.exception;


import lombok.Getter;

@Getter
public class AjouFinderException extends RuntimeException{
 private final ExceptionCode exceptionCode;

  public AjouFinderException(ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }
}
