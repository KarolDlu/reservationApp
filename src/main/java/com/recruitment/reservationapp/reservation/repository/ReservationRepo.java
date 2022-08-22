package com.recruitment.reservationapp.reservation.repository;

import com.recruitment.reservationapp.reservation.model.Apartment;
import com.recruitment.reservationapp.reservation.model.ApartmentReservation;
import com.recruitment.reservationapp.reservation.model.Reservation;
import com.recruitment.reservationapp.reservation.model.TenantReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    @Query("select case when count(r) = 0 then true else false end from Reservation r " +
            "where r.apartment.id = ?3 and (r.rentTime.startDate between ?1 and ?2 " +
            "or r.rentTime.endDate between ?1 and ?2 " +
            "or r.rentTime.startDate < ?1 and r.rentTime.endDate > ?2)")
    boolean isDateAvailable(LocalDate startDate, LocalDate endDate, Long apartmentId);

    @Query("select case when count(r) = 0 then true else false end from Reservation r " +
            "where r.apartment = ?3 and (r.rentTime.startDate between ?1 and ?2 " +
            "or r.rentTime.endDate between ?1 and ?2 " +
            "or r.rentTime.startDate < ?1 and r.rentTime.endDate > ?2)")
    boolean isDateAvailable(LocalDate startDate, LocalDate endDate, Apartment apartment);

    List<TenantReservation> findByTenantId(Long tenantId);

    List<ApartmentReservation> findByApartmentId(Long apartmentId);
}
