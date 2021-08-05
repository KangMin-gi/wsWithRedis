package com.flowerbun.ws.exception;

public class Common500Exception extends RuntimeException {

  private Common500Exception(String msg) {
    super(msg);
  }

  public static Common500Exception of(Errors errors) {
    return new Common500Exception(errors.getErrorMessage());
  }

  public static void throwException(Errors errors) {
    throw new Common500Exception(errors.getErrorMessage());
  }
}
