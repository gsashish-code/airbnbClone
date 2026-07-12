package com.ashish.airbnbclone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
public class Hotel extends BaseEntity {
    @Column(nullable = false)
    private String name;

    private String city;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "TEXT[]")
    private String[] photos;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "TEXT[]")
    private String[] amenities;

    @Column(nullable = false)
    private Boolean active;

    @Embedded
    private HotelContactInfo contactInfo;
}
