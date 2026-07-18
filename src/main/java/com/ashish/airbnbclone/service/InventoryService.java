package com.ashish.airbnbclone.service;


import com.ashish.airbnbclone.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventory(Room room);
}
