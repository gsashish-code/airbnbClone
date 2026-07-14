package com.ashish.airbnbclone.service.impl;


import com.ashish.airbnbclone.dto.RoomDto;
import com.ashish.airbnbclone.entity.Hotel;
import com.ashish.airbnbclone.entity.Room;
import com.ashish.airbnbclone.exception.ResourceNotFoundException;
import com.ashish.airbnbclone.repository.HotelRepository;
import com.ashish.airbnbclone.repository.RoomRepository;
import com.ashish.airbnbclone.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private  final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("trying to get hotel with hotel id:{}",hotelId);
        Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with hotel id :"+hotelId+" not found"));

        Room room=modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room=roomRepository.save(room);
        // TODO: Create inventory for room and hotel is active
        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRooms(Long hotelId) {
        log.info("trying to get hotel with hotel id:{}",hotelId);
        Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with hotel id :"+hotelId+" not found"));

        return hotel
                .getRooms()
                .stream()
                .map((room)->modelMapper.map(room,RoomDto.class)).collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoom(Long roomId) {
        log.info("trying to get room with room id:{}",roomId);
        Room room=
        roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room with room id :"+roomId+" not found"));
        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("trying to check room exist with room id:{}",roomId);
        boolean isRoomExist=
                roomRepository.existsById(roomId);
        if(isRoomExist){
            roomRepository.deleteById(roomId);
            // TODO: handle inventory
        }else{
            throw  new ResourceNotFoundException("Room with room id: "+roomId+" not found");
        }
    }
}
