package com.recruitment.reservationapp.reservation.service;

import com.recruitment.reservationapp.reservation.exceptions.UnavailableRentTimeException;
import com.recruitment.reservationapp.reservation.model.Area;
import com.recruitment.reservationapp.reservation.model.Price;
import com.recruitment.reservationapp.reservation.model.*;
import com.recruitment.reservationapp.reservation.payload.ReservationRequest;
import com.recruitment.reservationapp.reservation.repository.ApartmentRepo;
import com.recruitment.reservationapp.reservation.repository.ReservationRepo;
import com.recruitment.reservationapp.reservation.repository.TenantRepo;
import com.recruitment.reservationapp.reservation.service.impl.ReservationServiceImpl;
import org.assertj.core.api.Assertions;
import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepo reservationRepo;

    @Mock
    private ApartmentRepo apartmentRepo;

    @Mock
    private TenantRepo tenantRepo;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Apartment apartment;

    private Tenant tenant;

    @Before
    public void init() {
        Price price = new Price(Currency.getInstance("PLN"), BigDecimal.valueOf(97.4));
        Area area = new Area(153.2);
        Lessor lessor = new Lessor("Taylor Hobbs");
        apartment = new Apartment("Warsaw Office", price, area, "Short description", lessor);
        tenant = new Tenant("John Doe");
    }

    @Test
    public void makeReservation_shouldMakeReservation() {
        LocalDate startDate = LocalDate.of(2022, 9, 12);
        LocalDate endDate = LocalDate.of(2022, 9, 20);
        RentTime expectedRentTime = new RentTime(startDate, endDate);

        when(reservationRepo.isDateAvailable(startDate, endDate, 1L)).thenReturn(true);
        when(apartmentRepo.findById(1L)).thenReturn(Optional.of(apartment));
        when(tenantRepo.findById(1L)).thenReturn(Optional.of(tenant));
        when(reservationRepo.save(any(Reservation.class))).thenAnswer(input -> input.getArgument(0));
        ReservationRequest reservationRequest = new ReservationRequest(1L, 1L, startDate, endDate);
        Reservation reservation = reservationService.makeReservation(reservationRequest);

        Assertions.assertThat(reservation.getApartment()).isEqualTo(apartment);
        Assertions.assertThat(reservation.getTenant()).isEqualTo(tenant);
        Assertions.assertThat(reservation.getCost()).isEqualTo(apartment.getTotalPrice());
        Assertions.assertThat(reservation.getRentTime()).isEqualTo(expectedRentTime);
    }

    @Test
    public void makeReservation_whenRentTimeUnavailable_thenThrowException() {
        LocalDate startDate = LocalDate.of(2022, 9, 12);
        LocalDate endDate = LocalDate.of(2022, 9, 20);

        when(reservationRepo.isDateAvailable(startDate, endDate, 1L)).thenReturn(false);
        ReservationRequest reservationRequest = new ReservationRequest(1L, 1L, startDate, endDate);

        Assertions.assertThatThrownBy(() -> reservationService.makeReservation(reservationRequest))
                .isInstanceOf(UnavailableRentTimeException.class);
    }

    @Test
    public void makeReservation_whenApartmentNotExists_thenThrowException() {
        LocalDate startDate = LocalDate.of(2022, 9, 12);
        LocalDate endDate = LocalDate.of(2022, 9, 20);

        when(reservationRepo.isDateAvailable(startDate, endDate, 1L)).thenReturn(true);
        when(apartmentRepo.findById(1L)).thenReturn(Optional.empty());
        ReservationRequest reservationRequest = new ReservationRequest(1L, 1L, startDate, endDate);

        Assertions.setMaxStackTraceElementsDisplayed(1);
        Assertions.assertThatThrownBy(() -> reservationService.makeReservation(reservationRequest))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("No row with the given identifier exists: [Apartment#1]");
    }
}
