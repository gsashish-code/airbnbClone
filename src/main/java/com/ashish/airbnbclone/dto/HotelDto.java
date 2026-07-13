package com.ashish.airbnbclone.dto;

import com.ashish.airbnbclone.entity.HotelContactInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "city cannot be empty")
    private String city;
    private String[] photos;
    private String[] amenities;
    private Boolean active;
    private HotelContactInfoDto contactInfo;
}
