package com.recruitment.reservationapp.reservation.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RentTimeUpdate {

    private LocalDate startDate;

    private LocalDate endDate;
}
