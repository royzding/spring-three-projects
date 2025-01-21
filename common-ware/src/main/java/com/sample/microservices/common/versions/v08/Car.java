package com.sample.microservices.common.versions.v08;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private int Id;
    private String name;
    private long price;

    public static List<Car> getCarList() {

        List<Car> list = new ArrayList<>();
        list.add(new Car(1, "Toyota", 80000));
        list.add(new Car(2, "Honda", 90000));
        list.add(new Car(3, "Lincoln", 120000));
        list.add(new Car(4, "Tesla", 200000));
        list.add(new Car(5, "Ford", 150000));


        return list;
    }

    public static List<Car> getDuplicatedCarList() {

        List<Car> list = getCarList();

        list.add(new Car(6, "Honda", 100000));
        list.add(new Car(7, "Honda", 110000));

        return list;
    }

    public static List<Car> getDuplicatedCarList2() {

        List<Car> list = getDuplicatedCarList();

        list.add(new Car(8, "Toyota", 100000));
        list.add(new Car(9, "Toyota", 110000));

        return list;
    }

    public static List<Car> getRandomCarList() {

        List<Car> list = new ArrayList<>();
        list.add(new Car(5, "Toyota", 80000));
        list.add(new Car(2, "Honda", 90000));
        list.add(new Car(1, "Lincoln", 120000));
        list.add(new Car(7, "Honda", 110000));
        list.add(new Car(4, "Tesla", 200000));
        list.add(new Car(3, "Ford", 150000));
        list.add(new Car(6, "Honda", 100000));

        return list;
    }

}


