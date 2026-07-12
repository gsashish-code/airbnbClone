package com.ashish.airbnbclone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(
        name = "hotel_room_unique_date",
        columnNames = {"hotel_id","room_id","date"}
))
public class Inventory extends  BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id",nullable = false)
    private  Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id",nullable = false)
    private  Room room;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false,columnDefinition = "INTEGER DEFAULT 0")
    private Integer bookedCount;

    @Column(nullable = false)
    private Integer totalCount;

    @Column(nullable = false,precision = 5,scale = 2)
    private BigDecimal surgeFactor;

    @Column(nullable = false,precision = 5,scale = 2)
    private BigDecimal price; // base price * surgePrice

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean closed;
}
