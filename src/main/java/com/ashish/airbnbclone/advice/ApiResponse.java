package com.ashish.airbnbclone.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // not send null things
public class ApiResponse<T> {
  private T data;
  private LocalDateTime timestamp;
  private ApiError error;

  public ApiResponse() {
    this.timestamp = LocalDateTime.now();
  }

  public ApiResponse(T data) {
    this();
    this.data = data;
  }

  public ApiResponse(ApiError error) {
    this();
    this.error = error;
  }
}
