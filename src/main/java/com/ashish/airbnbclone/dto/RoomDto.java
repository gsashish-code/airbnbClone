package com.ashish.airbnbclone.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class RoomDto {
  private Long id;
  private String type;

  @Positive(message = "base price cannot be negative and zero")
  private BigDecimal basePrice;

  private String[] photos;
  private String[] amenities;
  private Integer totalCount;
  private Integer capacity;
}
