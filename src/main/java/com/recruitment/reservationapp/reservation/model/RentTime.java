package com.recruitment.reservationapp.reservation.model;

import com.recruitment.reservationapp.reservation.exceptions.InvalidRentTimeException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class RentTime {

    private LocalDate startDate;

    private LocalDate endDate;

    public RentTime(LocalDate startDate, LocalDate endDate) {
        if (!startDate.isAfter(LocalDate.now()) && !endDate.isAfter(startDate)) {
            throw new InvalidRentTimeException(startDate, endDate);
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }
}
