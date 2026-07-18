package com.ashish.airbnbclone.controller;

import com.ashish.airbnbclone.dto.RoomDto;
import com.ashish.airbnbclone.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admins/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {
  private final RoomService roomService;

  @PostMapping
  public ResponseEntity<RoomDto> createNewRoom(
      @PathVariable Long hotelId, @RequestBody RoomDto roomDto) {
    log.info("Creating room in hotel id:{}", hotelId);
    RoomDto room = roomService.createNewRoom(hotelId, roomDto);
    return new ResponseEntity<>(room, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<RoomDto>> getAllRoom(@PathVariable Long hotelId) {
    List<RoomDto> rooms = roomService.getAllRooms(hotelId);
    return ResponseEntity.ok(rooms);
  }

  @GetMapping("/{roomId}")
  public ResponseEntity<RoomDto> getRoom(@PathVariable Long hotelId, @PathVariable Long roomId) {
    return ResponseEntity.ok(roomService.getRoom(roomId));
  }

  @DeleteMapping("/{roomId}")
  public ResponseEntity<Boolean> deleteRoom(@PathVariable Long hotelId, @PathVariable Long roomId) {
    roomService.deleteRoomById(roomId);
    return ResponseEntity.ok(true);
  }
}
