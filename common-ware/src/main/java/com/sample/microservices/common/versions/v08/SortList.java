package com.sample.microservices.common.versions.v08;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class SortList {

    //sort a list
    public static void sortList() {

        List<Car> list = Car.getDuplicatedCarList();//Car.getCarList();

        list.forEach(System.out::println);

        //sort by price
        Collections.sort(list, new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return (int) (c1.getPrice() - c2.getPrice());
            }
        });

        list.forEach(System.out::println);

        list = Car.getDuplicatedCarList();

        //sort by name
        Collections.sort(list, new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });

        list.forEach(System.out::println);

        list = Car.getDuplicatedCarList();

        //sort with Interface Function
        list.sort(new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });

        //sort with Lambda Expression
        list.sort((Car c1, Car c2) -> c1.getName().compareTo(c2.getName()));

        Comparator<Car> carComparator = (Car c1, Car c2) -> c1.getName().compareTo(c2.getName());
        list.sort(carComparator);

    }

    //sorted a list
    public static void sortedList() {

        List<String> list = Arrays.asList("0", "e", "9", "A", "Z", "1", "B", "Y", "4", "a", "c", "3");

        System.out.println(list);

        List<String> sortedList = list.stream().sorted().toList();

        System.out.println(sortedList);

        List<String> reverseSortedList = list.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        System.out.println(reverseSortedList);

        List<Car> carList = Car.getDuplicatedCarList();

        System.out.println(carList);

        List<Car> sortedCarListByName = carList
                .stream()
                .sorted(Comparator.comparing(Car::getName))
                .toList();

        System.out.println(sortedCarListByName);

        List<Car> reverseSortedCarListByName = carList
                .stream()
                .sorted(Comparator.comparing(Car::getName).reversed())
                .toList();

        System.out.println(reverseSortedCarListByName);
    }

}


