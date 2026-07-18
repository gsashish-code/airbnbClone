package com.ashish.airbnbclone.dto;

import com.ashish.airbnbclone.entity.Guest;
import com.ashish.airbnbclone.entity.Hotel;
import com.ashish.airbnbclone.entity.Room;
import com.ashish.airbnbclone.entity.User;
import com.ashish.airbnbclone.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private HotelDto hotel;
    private RoomDto room;
    private UserDto user;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private  LocalDate checkOutDate;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
    private LocalDate createdAt;
}
