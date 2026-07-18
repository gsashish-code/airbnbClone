package com.ashish.airbnbclone.entity;

import com.ashish.airbnbclone.entity.enums.Gender;
import jakarta.persistence.*;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Guest extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private Integer age;

  @ManyToMany(mappedBy = "guests")
  private Set<Booking> bookings;
}
