package com.recruitment.reservationapp.reservation.model;

import com.recruitment.reservationapp.reservation.exceptions.NegativePriceValueException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class Price {

    private Currency currency;

    @Column(name = "price")
    private BigDecimal value;

    public Price(Currency currency, BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativePriceValueException();
        }

        this.currency = currency;
        this.value = value;
    }

    public Price multiply(BigDecimal multiplier) {
        return new Price(currency, this.value.multiply(multiplier).setScale(2, RoundingMode.HALF_UP));
    }
}
