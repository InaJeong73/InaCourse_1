package com.ajoufinder.common.exception.board;

import com.ajoufinder.common.exception.AjouFinderException;
import com.ajoufinder.common.exception.ExceptionCode;

public class BoardException extends AjouFinderException {

  public BoardException(ExceptionCode exceptionCode) {
    super(exceptionCode);
  }
}
