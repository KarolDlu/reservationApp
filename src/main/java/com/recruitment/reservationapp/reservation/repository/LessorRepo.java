package com.recruitment.reservationapp.reservation.repository;

import com.recruitment.reservationapp.reservation.model.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessorRepo extends JpaRepository<Lessor, Long> {
}
