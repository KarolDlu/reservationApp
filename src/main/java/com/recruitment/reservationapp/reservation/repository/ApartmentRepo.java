package com.recruitment.reservationapp.reservation.repository;

import com.recruitment.reservationapp.reservation.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Long> {
}
