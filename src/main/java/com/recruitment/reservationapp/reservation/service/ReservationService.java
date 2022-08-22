package com.recruitment.reservationapp.reservation.service;

import com.recruitment.reservationapp.reservation.model.ApartmentReservation;
import com.recruitment.reservationapp.reservation.payload.RentTimeUpdate;
import com.recruitment.reservationapp.reservation.payload.ReservationRequest;
import com.recruitment.reservationapp.reservation.model.Reservation;
import com.recruitment.reservationapp.reservation.model.TenantReservation;

import java.util.List;

public interface ReservationService {

    Reservation makeReservation(ReservationRequest reservationRequest);

    Reservation editReservation(Long reservationId, RentTimeUpdate rentTimeUpdate);

    List<TenantReservation> getTenantReservations(Long tenantId);

    List<ApartmentReservation> getApartmentReservations(Long apartmentId);
}
