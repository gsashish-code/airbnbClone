package com.ashish.airbnbclone.dto;

import com.ashish.airbnbclone.entity.enums.BookingStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Data
public class BookingDto {
  private Long id;
  private HotelDto hotel;
  private RoomDto room;
  private UserDto user;
  private Integer roomsCount;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;
  private BookingStatus bookingStatus;
  private Set<GuestDto> guests;
  private LocalDate createdAt;
}
