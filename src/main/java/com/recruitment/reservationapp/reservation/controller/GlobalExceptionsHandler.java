package com.recruitment.reservationapp.reservation.controller;

import com.recruitment.reservationapp.reservation.exceptions.InvalidRentTimeException;
import com.recruitment.reservationapp.reservation.exceptions.NegativeAreaValueException;
import com.recruitment.reservationapp.reservation.exceptions.NegativePriceValueException;
import com.recruitment.reservationapp.reservation.exceptions.UnavailableRentTimeException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class GlobalExceptionsHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleObjectNotFoundException(ObjectNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NegativePriceValueException.class)
    public ResponseEntity<String> handleNegativeValueException(NegativePriceValueException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRentTimeException.class)
    public ResponseEntity<String> handleInvalidRentTimeException(InvalidRentTimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NegativeAreaValueException.class)
    public ResponseEntity<String> handleNegativeAreaValueException(NegativeAreaValueException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnavailableRentTimeException.class)
    public ResponseEntity<String> handleUnavailableRentTimeException(UnavailableRentTimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
