package com.recruitment.reservationapp.reservation.model;

public interface ApartmentReservation {

    Long getId();

    Tenant getTenant();

    RentTime getRentTime();

    Price getCost();
}
