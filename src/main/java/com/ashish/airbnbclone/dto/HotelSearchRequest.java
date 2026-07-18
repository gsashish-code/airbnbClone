package com.ashish.airbnbclone.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Data;

@Data
public class HotelSearchRequest {
  @NotBlank(message = "city cannot be null, city required to searching")
  private String city;

  private LocalDate startDate;
  private LocalDate endDate;
  private Integer roomCount;

  private Integer page = 0;
  private Integer size = 10;
}
