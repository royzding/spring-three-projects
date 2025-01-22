package com.sample.microservices.common.versions.v08;

import java.util.*;
import java.util.stream.Collectors;


public class FilterAndFind {

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

    //filter a list to find first
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

    //filter a list to find last
    public static void findLast() {

        List<Car> carList = Car.getDuplicatedCarList().stream()
                .filter(c -> (!c.getName().equalsIgnoreCase("Honda")) &&
                        (c.getPrice() > 90000))
                .toList();

        System.out.println(carList);

        //using Stream.reduce((first, second) -> second)
        System.out.println(carList.stream().reduce((first, second) -> second).orElse(null));

        //using Stream.skip(stream.count()-1)
        System.out.println(carList.stream().skip(carList.size()-1).findFirst().orElse(null));

    }

    //limit a list to n elements
    public static void limitList() {

        List<Car> carList = Car.getDuplicatedCarList();

        System.out.println(carList);

        //using Stream.reduce((first, second) -> second)
        System.out.println(carList.stream().limit(3).toList());

    }


    //filter a list to find duplicates
    public static void findDuplicates() {

        List<Car> carList = Car.getDuplicatedCarList2();

        System.out.println(carList);

        Set<String> duplicatedCar = new HashSet<>();

        //using Set.add as false filter

        Set<String> car1 = carList
                .stream()
                .map(Car::getName)
                .filter(name ->!duplicatedCar.add(name))
                .collect(Collectors.toSet());

        System.out.println(car1);

        Set<String> car2 = carList
                .stream()
                .collect(Collectors.groupingBy(Car::getName, Collectors.counting())).entrySet()
                .stream()
                .filter(e->e.getValue()>1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        System.out.println(car2);

        List<String> carNames = carList.stream().map(Car::getName).toList();

        Set<String> car3 = carNames
                .stream()
                .filter(name->Collections.frequency(carNames, name)>1)
                .collect(Collectors.toSet());

        System.out.println(car3);

        //total duplicate count
        System.out.println(carNames.stream().filter(x->Collections.frequency(carNames, x)>1).count());

        //distinct duplicate count
        System.out.println(carNames.stream().filter(x->Collections.frequency(carNames, x)>1).distinct().count());

    }


}


