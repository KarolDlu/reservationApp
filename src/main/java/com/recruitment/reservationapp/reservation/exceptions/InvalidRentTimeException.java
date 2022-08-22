package com.recruitment.reservationapp.reservation.exceptions;

import java.time.LocalDate;

public class InvalidRentTimeException extends RuntimeException {

    public InvalidRentTimeException(LocalDate startDate, LocalDate endDate) {
        super("Invalid RentTime arguments {startDate: " + startDate + ", endDate: " + endDate + "}");
    }
}
