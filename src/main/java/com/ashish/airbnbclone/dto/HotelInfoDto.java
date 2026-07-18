package com.ashish.airbnbclone.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class HotelInfoDto {
  private HotelDto hotel;
  private List<RoomDto> rooms;
}
