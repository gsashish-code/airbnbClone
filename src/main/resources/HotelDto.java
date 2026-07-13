package com.ashish.airbnbclone.dto;

import javax.validation.constraints.NotBlank;
import com.ashish.airbnbclone.entity.HotelContactInfo;
import lombok.Data;


@Data
public class HotelDto {
    private Long id;
    @NotBlank
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private Boolean active;
    private HotelContactInfo contactInfo;
}
