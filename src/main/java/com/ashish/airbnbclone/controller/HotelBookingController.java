package com.ashish.airbnbclone.controller;

import com.ashish.airbnbclone.dto.BookingDto;
import com.ashish.airbnbclone.dto.BookingRequest;
import com.ashish.airbnbclone.repository.HotelRepository;
import com.ashish.airbnbclone.service.HotelBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("bookings")
public class HotelBookingController {

    private HotelBookingService hotelBookingService;
    @PostMapping
    public ResponseEntity<BookingDto> initializeBooking(@RequestBody BookingRequest bookingRequest){
    return ResponseEntity.ok(hotelBookingService.initializeBooking(bookingRequest));
    }

}
