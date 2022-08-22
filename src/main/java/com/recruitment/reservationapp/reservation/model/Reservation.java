package com.recruitment.reservationapp.reservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private RentTime rentTime;

    @ManyToOne
    private Apartment apartment;

    @ManyToOne
    private Tenant tenant;

    @Embedded
    private Price cost;

    public Reservation(RentTime rentTime, Apartment apartment, Tenant tenant, Price cost) {
        this.rentTime = rentTime;
        this.apartment = apartment;
        this.tenant = tenant;
        this.cost = cost;
    }

    public void updateRentTime(RentTime rentTime){
        this.rentTime = rentTime;
    }
}
