package com.ajoufinder.common.exception.user;

import com.ajoufinder.common.exception.AjouFinderException;
import com.ajoufinder.common.exception.ExceptionCode;

public class UserException extends AjouFinderException {
  public UserException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
