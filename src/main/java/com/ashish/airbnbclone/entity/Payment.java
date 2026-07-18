package com.ashish.airbnbclone.entity;

import com.ashish.airbnbclone.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Payment extends  BaseEntity{

    @Column(unique = true,nullable = false)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.LAZY)
    private  Booking booking;

}
