package com.sample.microservices.common.versions.v08;

import java.util.List;
import java.util.Objects;


public class FilterList {

    //filter a list
    public static void filterList() {

        List<Car> list;

        list = Car.getDuplicatedCarList().stream()
                .filter(c -> c.getPrice() > 90000)
                .toList();

        list.forEach(System.out::println);

        List<ColorCar> cList = Car.getDuplicatedCarList()
                .stream().map(c ->{

                    if(c.getPrice() > 100000) {
                        return new ColorCar(c, "White");
                    } else {
                        return new ColorCar(c, "Black");
                    }

                })
                .toList();

        System.out.println(cList);

        Car car;
        car = Car.getDuplicatedCarList().stream()
                .filter(c -> c.getName().equalsIgnoreCase("Honda"))
                .findAny()
                .orElse(null);

        System.out.println(car);


        car = Car.getDuplicatedCarList().stream()
                .filter(c -> (c.getName().equalsIgnoreCase("Honda")) &&
                        (c.getPrice() > 90000))
                .findAny()
                .orElse(null);

        System.out.println(car);

    }

    //filter null from a list
    public static void filterNullFromList() {

        List<Car> list = Car.getDuplicatedCarList();

        list.add(null);
        list.add(null);

        System.out.println(list);

        System.out.println(list.stream().filter(Objects::nonNull).toList());
        System.out.println(list.stream().filter(Objects::isNull).toList());

    }

    //filter a list to find any
    public static void findAny() {

        Car car = Car.getDuplicatedCarList().stream()
                .filter(c -> c.getName().equalsIgnoreCase("Honda"))
                .findAny()
                .orElse(null);

        System.out.println(car);


        car = Car.getDuplicatedCarList().stream()
                .filter(c -> (c.getName().equalsIgnoreCase("Honda")) &&
                        (c.getPrice() > 90000))
                .findAny()
                .orElse(null);

        System.out.println(car);

    }

    //filter a list to find any
    public static void findFirst() {

        Car car = Car.getDuplicatedCarList().stream()
                .filter(c -> c.getName().equalsIgnoreCase("Honda"))
                .findFirst()
                .orElse(null);

        System.out.println(car);


        car = Car.getDuplicatedCarList().stream()
                .filter(c -> (c.getName().equalsIgnoreCase("Honda")) &&
                        (c.getPrice() > 90000))
                .findFirst()
                .orElse(null);

        System.out.println(car);

    }
}


