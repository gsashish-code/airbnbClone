package com.ashish.airbnbclone.dto;

import com.ashish.airbnbclone.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private  Long id;
    private  String name;

    private  String email;

    private  String password; //encode

    private Set<Role> roles;
}
