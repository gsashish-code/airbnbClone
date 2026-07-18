package com.ashish.airbnbclone.service.impl;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.dto.HotelSearchRequest;
import com.ashish.airbnbclone.entity.Hotel;
import com.ashish.airbnbclone.entity.Inventory;
import com.ashish.airbnbclone.entity.Room;
import com.ashish.airbnbclone.repository.HotelRepository;
import com.ashish.airbnbclone.repository.InventoryRepository;
import com.ashish.airbnbclone.service.InventoryService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
  private final ModelMapper modelMapper;
  private final InventoryRepository inventoryRepository;
  private final HotelRepository hotelRepository;

  @Override
  public void initializeRoomForAYear(Room room) {
    LocalDate today = LocalDate.now();
    LocalDate endDate = today.plusYears(1);
    for (; !today.isAfter(endDate); today = today.plusDays(1)) {
      Inventory inventory =
          Inventory.builder()
              .hotel(room.getHotel())
              .room(room)
              .bookedCount(0)
              .city(room.getHotel().getCity())
              .date(today)
              .price(room.getBasePrice())
              .surgeFactor(BigDecimal.ONE)
              .totalCount(room.getTotalCount())
              .closed(false)
              .build();
      inventoryRepository.save(inventory);
    }
  }

  @Override
  public void deleteAllInventory(Room room) {
    LocalDate today = LocalDate.now();
    inventoryRepository.deleteByRoom(room);
  }

  @Override
  public Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest)
      throws BadRequestException {
    log.info("searching hotel:{}", hotelSearchRequest);
    Pageable pageable =
        PageRequest.of(
            hotelSearchRequest.getPage(),
            hotelSearchRequest.getSize() == null ? 10 : hotelSearchRequest.getSize());

    // both is null
    if (hotelSearchRequest.getStartDate() == null && hotelSearchRequest.getEndDate() == null) {

      Page<Hotel> hotel = hotelRepository.findByCity(hotelSearchRequest.getCity(), pageable);
      return hotel.map(element -> modelMapper.map(element, HotelDto.class));
    }
    // one of the date is null
    if ((hotelSearchRequest.getStartDate() == null) || (hotelSearchRequest.getEndDate() == null)) {
      throw new BadRequestException("Both startDate and endDate must be provided together.");
    }

    Integer roomCount =
        hotelSearchRequest.getRoomCount() == null ? 1 : hotelSearchRequest.getRoomCount();
    long dateCount =
        ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate());

    Page<Hotel> hotel =
        inventoryRepository.findHotelWithAvailableRepository(
            hotelSearchRequest.getCity(),
            hotelSearchRequest.getStartDate(),
            hotelSearchRequest.getEndDate(),
            roomCount,
            dateCount,
            pageable);
    log.info("returning response of Data: {}", hotel);
    return hotel.map(element -> modelMapper.map(element, HotelDto.class));
  }
}
