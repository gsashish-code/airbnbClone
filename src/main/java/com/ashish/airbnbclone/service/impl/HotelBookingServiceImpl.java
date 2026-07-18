package com.ashish.airbnbclone.service.impl;

import com.ashish.airbnbclone.dto.BookingDto;
import com.ashish.airbnbclone.dto.BookingRequest;
import com.ashish.airbnbclone.entity.*;
import com.ashish.airbnbclone.entity.enums.BookingStatus;
import com.ashish.airbnbclone.exception.ResourceNotFoundException;
import com.ashish.airbnbclone.repository.HotelBookingRepository;
import com.ashish.airbnbclone.repository.HotelRepository;
import com.ashish.airbnbclone.repository.InventoryRepository;
import com.ashish.airbnbclone.repository.RoomRepository;
import com.ashish.airbnbclone.service.HotelBookingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HotelBookingServiceImpl implements HotelBookingService {
    private final HotelBookingRepository hotelBookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingDto initializeBooking(BookingRequest bookingRequest) {
        Hotel hotel=hotelRepository.findById(bookingRequest.getHotelId()).orElseThrow(()->new ResourceNotFoundException("hotel with the id not found: "+bookingRequest.getHotelId()));
        Room room=roomRepository.findById(bookingRequest.getRoomId()).orElseThrow(()->new ResourceNotFoundException("room not found with id:"+bookingRequest.getRoomId()));
        List<Inventory> inventoryList=inventoryRepository.findANDLockAvailableInventory(
                room.getId(),
                bookingRequest.getCheckInDate(),
                bookingRequest.getCheckOutDate(),
                bookingRequest.getRoomCount()
        );
        long daysCount= ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(),bookingRequest.getCheckOutDate());
        if(inventoryList.size()!=daysCount){
            throw  new IllegalAccessException("Room is not available now");
        }

        // reserve the room
        for(Inventory inventory:inventoryList){
            inventory.setBookedCount(inventory.getBookedCount()- bookingRequest.getRoomCount());
        }

        inventoryRepository.saveAll(inventoryList);
        // TODO: user to temp
        User user=new User();
        user.setId(1L);
        // create booking
        Booking booking=Booking.builder().bookingStatus(BookingStatus.RESERVE)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequest.getCheckInDate())
                .checkOutDate(bookingRequest.getCheckOutDate())
                .user(user)
                .roomsCount(bookingRequest.getRoomCount())
                .amount(BigDecimal.ONE)
                .build();

        // TODO CREATE Dynamic price
        booking=hotelBookingRepository.save(booking);


        return modelMapper.map(booking,BookingDto.class);
    }
}
