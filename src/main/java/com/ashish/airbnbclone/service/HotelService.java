package com.ashish.airbnbclone.service;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.dto.HotelInfoDto;

import java.util.List;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id, HotelDto hotelDto);
    Boolean deleteHotelById(Long id);
    List<HotelDto> getAllHotels();
    void activateHotel(Long id,Boolean status);

    HotelInfoDto getHotelInfoById(Long hotelId);
}
