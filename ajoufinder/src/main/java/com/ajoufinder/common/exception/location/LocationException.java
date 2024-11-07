package com.ajoufinder.common.exception.location;

import com.ajoufinder.common.exception.AjouFinderException;
import com.ajoufinder.common.exception.ExceptionCode;

public class LocationException extends AjouFinderException {
  public LocationException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
