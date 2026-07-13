package com.ashish.airbnbclone.service.impl;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.entity.Hotel;
import com.ashish.airbnbclone.exception.ResourceNotFoundException;
import com.ashish.airbnbclone.repository.HotelRepository;
import com.ashish.airbnbclone.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name: {} and full payload: {}",hotelDto.getName(),hotelDto);
        Hotel hotel=modelMapper.map(hotelDto,Hotel.class);
        // if user didn't pass active status setting false
        if(hotel.getActive()==null){
            hotel.setActive(false);
        }
        hotel=hotelRepository.save(hotel);
        log.info("Created new Hotel with id: {}",hotel.getId());
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Get hotel by id: {}",id);
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with id not found:"+id));

        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating Hotel By id: {} and payload: {}",id,hotelDto);
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with id not found:"+id));
        log.info("Found hotel with the id: {}",id);
        hotelDto.setId(hotel.getId());
        modelMapper.map(hotelDto,hotel);

        hotel=hotelRepository.save(hotel);
        log.info("hotel with this id Updated: {}",id);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public Boolean deleteHotelById(Long id) {
        log.info("Deleting Hotel By id: {} ",id);
        Boolean isExists=hotelRepository.existsById(id);
        if(!isExists){
            log.info("No Hotel Found hotel with the id: {}",id);
            return false;
        }

        log.info("Found hotel with the id: {}",id);
        hotelRepository.deleteById(id);
        log.info("hotel with this id Deleted: {}",id);
        return isExists;
    }

    @Override
    public List<HotelDto> getAllHotels() {
        log.info("Getting All hotels");
        List<Hotel> hotels= hotelRepository.findAll();
        List<HotelDto> hotelDtos = hotels.stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .toList();
        return hotelDtos;
    }
}
