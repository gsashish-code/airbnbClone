package com.ashish.airbnbclone.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room extends BaseEntity {
  @Column(nullable = false)
  private String type;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal basePrice;

  @Column(columnDefinition = "TEXT[]")
  private List<String> photos;

  @Column(columnDefinition = "TEXT[]")
  private List<String> amenities;

  @Column(nullable = false)
  private Integer totalCount;

  @Column(nullable = false)
  private Integer capacity;

  @ManyToOne
  @JoinColumn(name = "hotel_id", nullable = false)
  private Hotel hotel;
}
