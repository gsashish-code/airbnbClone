package com.ashish.airbnbclone.dto;

import com.ashish.airbnbclone.entity.User;
import com.ashish.airbnbclone.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {
  private Long id;
  private User user;
  private String name;
  private Gender gender;
  private Integer age;
}
