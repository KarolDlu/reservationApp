package com.recruitment.reservationapp.reservation.repository;

import com.recruitment.reservationapp.reservation.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepo extends JpaRepository<Tenant, Long> {
}
