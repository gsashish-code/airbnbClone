package com.ashish.airbnbclone.controller;

import com.ashish.airbnbclone.dto.BookingDto;
import com.ashish.airbnbclone.dto.BookingRequest;
import com.ashish.airbnbclone.dto.GuestDto;
import com.ashish.airbnbclone.service.HotelBookingService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

  private HotelBookingService hotelBookingService;

  @PostMapping("/init")
  public ResponseEntity<BookingDto> initializeBooking(@RequestBody BookingRequest bookingRequest) {
    return ResponseEntity.ok(hotelBookingService.initializeBooking(bookingRequest));
  }

  @PostMapping("/{bookingId}/addGuests")
  public ResponseEntity<BookingDto> addGuestsToBooking(
      @PathVariable("bookingId") Long bookingId, @RequestBody List<GuestDto> guests) {
    return ResponseEntity.ok(hotelBookingService.addGuests(bookingId, guests));
  }
}
