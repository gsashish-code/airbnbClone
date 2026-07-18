package com.ashish.airbnbclone.dto;

import com.ashish.airbnbclone.entity.enums.Role;
import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;

@Data
public class UserDto {
  private Long id;
  private String name;

  private String email;

  private String password; // encode

  private Set<Role> roles;
}
