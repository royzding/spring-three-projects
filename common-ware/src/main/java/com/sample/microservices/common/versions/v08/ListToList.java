package com.sample.microservices.common.versions.v08;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ListToList {

    //a list to another list
    public static void listToList() {

        List<ColorCar> cList = Car.getDuplicatedCarList()
                .stream().map(c -> {

                    if (c.getPrice() > 100000) {
                        return new ColorCar(c, "White");
                    } else {
                        return new ColorCar(c, "Black");
                    }

                })
                .toList();

        System.out.println(cList);

        List<String> nameList = Car.getDuplicatedCarList().stream()
                .filter(c -> c.getPrice() > 90000)
                .map(Car::getName)
                .toList();

        nameList.forEach(System.out::println);

        Set<String> nameSet = Car.getDuplicatedCarList().stream()
                .map(Car::getName)
                .collect(Collectors.toSet());

        System.out.println(nameSet);

    }

}


