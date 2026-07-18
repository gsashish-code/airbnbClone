package com.ashish.airbnbclone.service;

import com.ashish.airbnbclone.dto.BookingDto;
import com.ashish.airbnbclone.dto.BookingRequest;
import org.springframework.stereotype.Service;

@Service
public interface HotelBookingService {
    BookingDto initializeBooking(BookingRequest bookingRequest);
}
