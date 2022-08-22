package com.recruitment.reservationapp.reservation.controller;

import com.recruitment.reservationapp.reservation.model.ApartmentReservation;
import com.recruitment.reservationapp.reservation.model.Reservation;
import com.recruitment.reservationapp.reservation.model.TenantReservation;
import com.recruitment.reservationapp.reservation.payload.RentTimeUpdate;
import com.recruitment.reservationapp.reservation.payload.ReservationRequest;
import com.recruitment.reservationapp.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation makeReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.makeReservation(reservationRequest);
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<TenantReservation>> getTenantReservations(@PathVariable Long tenantId) {
        return ResponseEntity.ok(reservationService.getTenantReservations(tenantId));
    }

    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<ApartmentReservation>> getApartmentReservations(@PathVariable Long apartmentId) {
        return ResponseEntity.ok(reservationService.getApartmentReservations(apartmentId));
    }

    @PutMapping("{reservationId}")
    public ResponseEntity<Reservation> editReservation(@PathVariable Long reservationId, @RequestBody RentTimeUpdate rentTimeUpdate) {
        return ResponseEntity.ok(reservationService.editReservation(reservationId, rentTimeUpdate));
    }
}
