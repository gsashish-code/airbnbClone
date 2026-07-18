package com.ashish.airbnbclone.controller;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.dto.HotelInfoDto;
import com.ashish.airbnbclone.dto.HotelSearchRequest;
import com.ashish.airbnbclone.service.HotelService;
import com.ashish.airbnbclone.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowserController {
  private final InventoryService inventoryService;
  private final HotelService hotelService;

  @GetMapping("/search")
  public ResponseEntity<Page<HotelDto>> searchHotel(
      @RequestBody HotelSearchRequest hotelSearchRequest) throws BadRequestException {
    log.info("Searching values here:{} ", hotelSearchRequest);
    Page<HotelDto> page = inventoryService.searchHotels(hotelSearchRequest);
    return ResponseEntity.ok(page);
  }

  @GetMapping("/{hotelId}")
  public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {

    return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
  }
}
