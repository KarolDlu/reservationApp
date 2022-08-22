package com.recruitment.reservationapp.reservation.model;

public interface TenantReservation {

    Long getId();

    RentTime getRentTime();

    Apartment getApartment();

    Price getCost();
}
