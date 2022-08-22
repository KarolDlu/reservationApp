package com.recruitment.reservationapp.reservation.service.impl;

import com.recruitment.reservationapp.reservation.exceptions.UnavailableRentTimeException;
import com.recruitment.reservationapp.reservation.model.*;
import com.recruitment.reservationapp.reservation.payload.RentTimeUpdate;
import com.recruitment.reservationapp.reservation.payload.ReservationRequest;
import com.recruitment.reservationapp.reservation.repository.ApartmentRepo;
import com.recruitment.reservationapp.reservation.repository.ReservationRepo;
import com.recruitment.reservationapp.reservation.repository.TenantRepo;
import com.recruitment.reservationapp.reservation.service.ReservationService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepo reservationRepo;
    private final ApartmentRepo apartmentRepo;
    private final TenantRepo tenantRepo;

    @Autowired
    public ReservationServiceImpl(ReservationRepo reservationRepo, ApartmentRepo apartmentRepo, TenantRepo tenantRepo) {
        this.reservationRepo = reservationRepo;
        this.apartmentRepo = apartmentRepo;
        this.tenantRepo = tenantRepo;
    }

    public Reservation makeReservation(ReservationRequest reservationRequest) {
        LocalDate startDate = reservationRequest.getStartDate();
        LocalDate endDate = reservationRequest.getEndDate();
        if (!reservationRepo.isDateAvailable(startDate, endDate, reservationRequest.getApartmentId())) {
            throw new UnavailableRentTimeException();
        }

        Apartment apartment = apartmentRepo.findById(reservationRequest.getApartmentId())
                .orElseThrow(() -> new ObjectNotFoundException(reservationRequest.getApartmentId(), "Apartment"));
        Tenant tenant = tenantRepo.findById(reservationRequest.getTenantId())
                .orElseThrow(() -> new ObjectNotFoundException(reservationRequest.getTenantId(), "Tenant"));
        RentTime rentTime = new RentTime(startDate, endDate);

        return reservationRepo.save(new Reservation(rentTime, apartment, tenant, apartment.getTotalPrice()));
    }

    public Reservation editReservation(Long reservationId, RentTimeUpdate rentTimeUpdate) {
        Reservation reservation = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new ObjectNotFoundException(reservationId, "Reservation"));
        LocalDate startDate = rentTimeUpdate.getStartDate();
        LocalDate endDate = rentTimeUpdate.getEndDate();

        if (!reservationRepo.isDateAvailable(startDate, endDate, reservation.getApartment())) {
            throw new UnavailableRentTimeException();
        }

        RentTime updatedRentTime = new RentTime(startDate, endDate);
        reservation.updateRentTime(updatedRentTime);
        return reservationRepo.save(reservation);
    }

    public List<TenantReservation> getTenantReservations(Long tenantId) {
        return reservationRepo.findByTenantId(tenantId);
    }

    public List<ApartmentReservation> getApartmentReservations(Long apartmentId) {
        return reservationRepo.findByApartmentId(apartmentId);
    }
}
