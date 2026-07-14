package com.ashish.airbnbclone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

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

    @Embedded
    private HotelContactInfo contactInfo;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;
}
