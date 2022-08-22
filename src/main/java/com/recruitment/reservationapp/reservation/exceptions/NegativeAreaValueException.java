package com.recruitment.reservationapp.reservation.exceptions;

public class NegativeAreaValueException extends RuntimeException {

    public NegativeAreaValueException() {
        super("Can't set negative area value");
    }
}
