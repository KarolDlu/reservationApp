package com.recruitment.reservationapp.reservation.exceptions;

public class UnavailableRentTimeException extends RuntimeException {

    public UnavailableRentTimeException() {
        super("Given rent time is unavailable");
    }
}
