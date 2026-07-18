package com.ashish.airbnbclone.advice;

import com.ashish.airbnbclone.exception.ResourceNotFoundException;
import com.ashish.airbnbclone.exception.RoomNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(
      ResourceNotFoundException error) {
    ApiError apiError =
        ApiError.builder().status(HttpStatus.NOT_FOUND).message(error.getMessage()).build();
    return buildApiResponse(apiError);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(Exception error) {
    ApiError apiError =
        ApiError.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(error.getMessage())
            .build();
    return buildApiResponse(apiError);
  }

  @ExceptionHandler(RoomNotAvailableException.class)
  public ResponseEntity<ApiResponse<?>> handleRoomNotAvailable(RoomNotAvailableException ex) {
    ApiError apiError =
        ApiError.builder().status(HttpStatus.CONFLICT).message(ex.getMessage()).build();
    return buildApiResponse(apiError);
  }

  public ResponseEntity<ApiResponse<?>> buildApiResponse(ApiError apiError) {
    return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
  }
}
