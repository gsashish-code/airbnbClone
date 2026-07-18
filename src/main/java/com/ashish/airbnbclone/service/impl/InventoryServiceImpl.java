package com.ashish.airbnbclone.service.impl;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.dto.HotelSearchRequest;
import com.ashish.airbnbclone.entity.Hotel;
import com.ashish.airbnbclone.entity.Inventory;
import com.ashish.airbnbclone.entity.Room;
import com.ashish.airbnbclone.repository.InventoryRepository;
import com.ashish.airbnbclone.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final ModelMapper modelMapper;
    private InventoryRepository inventoryRepository;
    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today=LocalDate.now();
        LocalDate endDate=today.plusYears(1);
        for(;!today.isAfter(endDate);today=today.plusDays(1)){
            Inventory inventory=Inventory.builder()
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
            LocalDate today=LocalDate.now();
            inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("searching hotel:{}",hotelSearchRequest);
        Pageable pageable= PageRequest.of(hotelSearchRequest.getPage(),hotelSearchRequest.getSize());

        long dateCount= ChronoUnit.DAYS.between(hotelSearchRequest.getEndDate(), hotelSearchRequest.getEndDate());

        Page<Hotel> hotel= inventoryRepository.findHotelWithAvailableRepository(
                hotelSearchRequest.getCity(),
                hotelSearchRequest.getStartDate(),
                hotelSearchRequest.getEndDate(),
                hotelSearchRequest.getRoomCount(),
                dateCount,
                pageable
                );
        log.info("returning response of Data: {}",hotel);
        return hotel.map(element->modelMapper.map(element,HotelDto.class));
    }
}
