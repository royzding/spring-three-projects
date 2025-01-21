package com.sample.microservices.common.versions.v08;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ColorCar extends Car {

    private String color;

    public ColorCar(Car car, String color) {
        this.setId(car.getId());
        this.setName(car.getName());
        this.setPrice(car.getPrice());
        this.setColor(color);
    }

    @Override
    public String toString() {
        return "ColorCar(" +
                "id=" + this.getId() +
                ", name=" + this.getName() +
                ", price=" + this.getPrice() +
                ", color=" + this.color +
                ")";
    }

}