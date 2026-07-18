package com.ashish.airbnbclone.repository;


import com.ashish.airbnbclone.controller.HotelBookingController;
import com.ashish.airbnbclone.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelBookingRepository extends JpaRepository<Booking,Long> {


}
