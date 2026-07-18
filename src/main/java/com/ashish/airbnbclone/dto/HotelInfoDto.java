package com.ashish.airbnbclone.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class HotelInfoDto {
    private HotelDto hotel;
    private List<RoomDto> rooms;
}
