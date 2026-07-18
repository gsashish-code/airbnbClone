package com.ashish.airbnbclone.repository;

import com.ashish.airbnbclone.dto.HotelDto;
import com.ashish.airbnbclone.dto.HotelSearchRequest;
import com.ashish.airbnbclone.entity.Hotel;
import com.ashish.airbnbclone.entity.Inventory;
import com.ashish.airbnbclone.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByRoom(Room room);

    @Query("""
        SELECT DISTINCT i.hotel
        FROM Inventory  i
        WHERE  i.city= :city
            AND i.date BETWEEN :startDate AND :endDate
            AND i.closed = false
            AND (i.totalCount-i.bookedCount) >= :roomCount
        GROUP BY i.hotel,i.room
        HAVING COUNT(i.date) = :dateCount
""")
    Page<Hotel> findHotelWithAvailableRepository(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("noOfRooms") Integer roomCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );


    @Query(
            """
            SELECT i
            FROM Inventory  i
            WHERE i.room.id= :roomId
                  AND i.date BETWEEN :startDate AND :endDate
                  AND i.closed = false
                  AND (i.totalCount-i.bookedCount) >= :roomCount
            """
    )
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findANDLockAvailableInventory(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("noOfRooms") Integer roomCount
    );

}

