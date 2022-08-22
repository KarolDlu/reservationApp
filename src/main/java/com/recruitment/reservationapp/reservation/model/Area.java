package com.recruitment.reservationapp.reservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

@Embeddable
@NoArgsConstructor
@Getter
public class Area {

    @Column(name = "area")
    private BigDecimal value;

    public Area(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }

        this.value = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }
}
