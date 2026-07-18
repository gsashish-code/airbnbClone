package com.ashish.airbnbclone.service.impl;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.dto.HotelInfoDto;
import com.ashish.airbnbclone.dto.RoomDto;
import com.ashish.airbnbclone.entity.Hotel;
import com.ashish.airbnbclone.entity.Room;
import com.ashish.airbnbclone.exception.ResourceNotFoundException;
import com.ashish.airbnbclone.repository.HotelRepository;
import com.ashish.airbnbclone.repository.RoomRepository;
import com.ashish.airbnbclone.service.HotelService;
import com.ashish.airbnbclone.service.InventoryService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
  private final HotelRepository hotelRepository;
  private final ModelMapper modelMapper;
  private final InventoryService inventoryService;
  private final RoomRepository roomRepository;

  @Override
  public HotelDto createNewHotel(HotelDto hotelDto) {
    log.info(
        "Creating a new hotel with name: {} and full payload: {}", hotelDto.getName(), hotelDto);
    Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
    // if user didn't pass active status setting false
    if (hotel.getActive() == null) {
      hotel.setActive(false);
    }
    hotel = hotelRepository.save(hotel);
    log.info("Created new Hotel with id: {}", hotel.getId());
    return modelMapper.map(hotel, HotelDto.class);
  }

  @Override
  public HotelDto getHotelById(Long id) {
    log.info("Get hotel by id: {}", id);
    Hotel hotel =
        hotelRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel with id not found:" + id));

    return modelMapper.map(hotel, HotelDto.class);
  }

  @Override
  @Transactional
  public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
    log.info("Updating Hotel By id: {} and payload: {}", id, hotelDto);
    Hotel hotel =
        hotelRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel with id not found:" + id));
    log.info("Found hotel with the id: {}", id);
    hotelDto.setId(hotel.getId());
    modelMapper.map(hotelDto, hotel);

    hotel = hotelRepository.save(hotel);
    log.info("hotel with this id Updated: {}", id);
    return modelMapper.map(hotel, HotelDto.class);
  }

  @Override
  @Transactional
  public Boolean deleteHotelById(Long id) {
    log.info("Deleting Hotel By id: {} ", id);
    Hotel hotel =
        hotelRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this id:" + id));
    ;

    log.info("Found hotel with the id: {}", id);

    for (Room room : hotel.getRooms()) {
      inventoryService.deleteAllInventory(room);
      roomRepository.deleteById(room.getId());
      log.info("hotel room inventory and room Deleted: {}", id);
    }

    hotelRepository.deleteById(id);
    log.info("hotel with this id Deleted: {}", id);
    return true;
  }

  @Override
  public List<HotelDto> getAllHotels() {
    log.info("Getting All hotels");
    List<Hotel> hotels = hotelRepository.findAll();
    List<HotelDto> hotelDtos =
        hotels.stream().map(hotel -> modelMapper.map(hotel, HotelDto.class)).toList();
    return hotelDtos;
  }

  @Override
  @Transactional
  public void activateHotel(Long id, Boolean status) {
    log.info("trying to get user with particular id:{}", id);
    Hotel hotel =
        hotelRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this id:" + id));
    if (!hotel.getActive().equals(status)) {
      hotel.setActive(status);
      hotelRepository.save(hotel);
    } else {
      log.info(
          "status from db: {} trying to change:{} as status is same not calling db",
          hotel.getActive(),
          status);
    }

    for (Room room : hotel.getRooms()) {
      inventoryService.initializeRoomForAYear(room);
    }
  }

  @Override
  public HotelInfoDto getHotelInfoById(Long hotelId) {
    Hotel hotel =
        hotelRepository
            .findById(hotelId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Hotel not found with this id:" + hotelId));

    List<RoomDto> rooms =
        hotel.getRooms().stream().map(element -> modelMapper.map(element, RoomDto.class)).toList();

    return HotelInfoDto.builder()
        .rooms(rooms)
        .hotel(modelMapper.map(hotel, HotelDto.class))
        .build();
  }
}
