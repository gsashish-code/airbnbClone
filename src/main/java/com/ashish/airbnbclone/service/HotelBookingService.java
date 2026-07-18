package com.ashish.airbnbclone.service;

import com.ashish.airbnbclone.dto.BookingDto;
import com.ashish.airbnbclone.dto.BookingRequest;
import com.ashish.airbnbclone.dto.GuestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface HotelBookingService {
  BookingDto initializeBooking(BookingRequest bookingRequest);

  BookingDto addGuests(Long bookingId, List<GuestDto> guests);
}
