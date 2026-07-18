package com.ashish.airbnbclone.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hotel extends BaseEntity {
  @Column(nullable = false)
  private String name;

  private String city;

  @Column(columnDefinition = "TEXT[]")
  private String[] photos;

  @Column(columnDefinition = "TEXT[]")
  private String[] amenities;

  @Column(nullable = false)
  private Boolean active;

  @Embedded private HotelContactInfo contactInfo;

  @ManyToOne private User owner;

  @OneToMany(mappedBy = "hotel")
  private List<Room> rooms;
}
