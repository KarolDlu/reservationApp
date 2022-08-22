package com.recruitment.reservationapp.reservation.exceptions;

public class NegativePriceValueException extends RuntimeException {
    public NegativePriceValueException() {
        super("Can't set negative price value");
    }
}
