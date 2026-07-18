package com.ashish.airbnbclone.service;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.dto.HotelSearchRequest;
import com.ashish.airbnbclone.entity.Room;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;

public interface InventoryService {
  void initializeRoomForAYear(Room room);

  void deleteAllInventory(Room room);

  Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest) throws BadRequestException;
}
