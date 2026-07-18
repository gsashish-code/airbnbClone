package com.ashish.airbnbclone.exception;

public class RoomNotAvailableException extends RuntimeException {

  public RoomNotAvailableException(String message) {
    super(message);
  }
}
