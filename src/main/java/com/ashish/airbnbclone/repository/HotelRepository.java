package com.ashish.airbnbclone.repository;

import com.ashish.airbnbclone.entity.Hotel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
  Page<Hotel> findByCity(
      @NotBlank(message = "city cannot be null, city required to searching") String city,
      Pageable pageable);
}
