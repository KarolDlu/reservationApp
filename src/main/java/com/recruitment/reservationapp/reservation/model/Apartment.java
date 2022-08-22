package com.recruitment.reservationapp.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.RoundingMode;

@Entity
@NoArgsConstructor
@Getter
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Price unitPrice;

    @Embedded
    private Area area;

    private String description;

    @ManyToOne
    private Lessor owner;

    public Apartment(String name, Price unitPrice, Area area, String description, Lessor owner) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.area = area;
        this.description = description;
        this.owner = owner;
    }

    @JsonIgnore
    public Price getTotalPrice(){
        return unitPrice.multiply(area.getValue().setScale(2, RoundingMode.HALF_UP));
    }
}
