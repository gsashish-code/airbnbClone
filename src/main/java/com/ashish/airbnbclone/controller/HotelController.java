package com.ashish.airbnbclone.controller;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.service.HotelService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
  private final HotelService hotelService;
  private final ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<HotelDto> createHotel(@RequestBody @Valid HotelDto hotelDto) {
    log.info("Attempting to create hotel with name:{}", hotelDto.getName());
    HotelDto hotel = hotelService.createNewHotel(hotelDto);
    return new ResponseEntity<>(hotel, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<HotelDto>> getAllHotels() {
    log.info("Attempting to Get all hotels:");
    List<HotelDto> hotel = hotelService.getAllHotels();
    return ResponseEntity.ok(hotel);
  }

  @GetMapping("/{hotelId}")
  public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId) {
    log.info("Attempting to get hotel with id:{}", hotelId);
    HotelDto hotel = hotelService.getHotelById(hotelId);
    return ResponseEntity.ok(hotel);
  }

  @PutMapping("/{hotelId}")
  public ResponseEntity<HotelDto> updateHotelById(
      @PathVariable Long hotelId, @RequestBody HotelDto hotelDto) {
    log.info("Attempting to PUT hotel with id:{}", hotelId);
    HotelDto hotel = hotelService.updateHotelById(hotelId, hotelDto);
    return ResponseEntity.ok(hotel);
  }

  @DeleteMapping("/{hotelId}")
  public ResponseEntity<Boolean> deleteHotelById(@PathVariable Long hotelId) {
    log.info("Attempting to Get all hotels:");
    Boolean isDeleted = hotelService.deleteHotelById(hotelId);
    return ResponseEntity.ok(isDeleted);
  }

  @PatchMapping("/{hotelId}")
  public ResponseEntity<Boolean> activateHotelStatus(
      @PathVariable Long hotelId, @RequestParam Boolean activate) {
    log.info("Attempting to update status to:{} with id: {}", activate, hotelId);
    hotelService.activateHotel(hotelId, activate);
    return ResponseEntity.ok(true);
  }
}
