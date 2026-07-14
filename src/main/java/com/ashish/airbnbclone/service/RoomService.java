package com.ashish.airbnbclone.service;


import com.ashish.airbnbclone.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createNewRoom(Long hotelId,RoomDto room);
    List<RoomDto> getAllRooms(Long hotelId);
    RoomDto getRoom(Long roomId);
    void deleteRoomById(Long roomId);
}
