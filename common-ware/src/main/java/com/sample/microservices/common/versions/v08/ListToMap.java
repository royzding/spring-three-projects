package com.sample.microservices.common.versions.v08;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListToMap {

    //convert a list to a map
    public static void listToMap() {

        List<Car> list = Car.getCarList();

        // key = id, value - price
        Map<Integer, String> result1 = list.stream().collect(
                Collectors.toMap(Car::getId, Car::getName));

        System.out.println("Result 1 : " + result1);

        // key = name, value - price
        Map<String, Long> result2 = list.stream().collect(
                Collectors.toMap(Car::getName, Car::getPrice));

        System.out.println("Result 2 : " + result2);

        // Same with result1, just different syntax
        // key = id, value = name
        Map<Integer, String> result3 = list.stream().collect(
                Collectors.toMap(x -> x.getId(), x -> x.getName()));

        System.out.println("Result 3 : " + result3);
    }

    //convert a list to a map with duplicate keys
    public static void listWithDuplicatedKeyToMap() {

        List<Car> list = Car.getDuplicatedCarList();

        // key = name, value - price , but the key 'Honda' is duplicated!?
        // oldValue is the first added.
        // newValue is the last added.

        Map<String, Long> result1 = list.stream().collect(
                Collectors.toMap(Car::getName, Car::getPrice,
                        (oldValue, newValue) -> oldValue
                )
        );

        System.out.println("Result 1 : " + result1);

        // key = name, value - price , but the key 'Honda' is duplicated!?
        Map<String, Long> result2 = list.stream().collect(
                Collectors.toMap(Car::getName, Car::getPrice,
                        (oldValue, newValue) -> newValue
                )
        );

        System.out.println("Result 2 : " + result2);

    }

    //convert a list to a map with groupingBy
    public static void listToMapWithGroupingBy() {

        List<Car> list = Car.getDuplicatedCarList();

        //reverse the order
        List<String> result1 = list.stream()
                .sorted(Comparator.comparingLong(Car::getPrice).reversed())
                .map(Car::getName)
                .toList();

        System.out.println("Result 1 : " + result1);

        Map<String, Long> result2 = result1.stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()
                        ));

        System.out.println("Result 2 : " + result2);

        //reverse the order
        List<Car> result3 = list.stream()
                .sorted(Comparator.comparingLong(Car::getPrice).reversed()).toList();

        System.out.println("Result 3 : " + result3);

        Map<String, Long> result4 = result3.stream()
                .collect(
                        Collectors.groupingBy(Car::getName, Collectors.counting()
                        ));

        System.out.println("Result 4 : " + result4);

        Map<String, Long> result5= result3.stream()
                .collect(
                        Collectors.groupingBy(Car::getName, Collectors.summingLong(Car::getPrice)
                        ));

        System.out.println("Result 5 : " + result5);

        List<Car> list2 = Car.getDuplicatedCarList2();

        Map<Long, List<Car>> result6 = list2.stream()
                .collect(
                        Collectors.groupingBy(Car::getPrice)
                );

        System.out.println("Result 6 : " + result6);

        Map<Long, Set<String>> result7 = list2.stream()
                .collect(
                        Collectors.groupingBy(Car::getPrice,
                                Collectors.mapping(Car::getName, Collectors.toSet()))
                );

        System.out.println("Result 7 : " + result7);
    }

    //sort a list and convert to a map
    public static void listSortedKeyToMap() {

        List<Car> list = Car.getDuplicatedCarList();

        //reverse the order
        List<Car> result1 = list.stream()
                .sorted(Comparator.comparingLong(Car::getPrice).reversed()).toList();

        System.out.println("Result 1 : " + result1);

        Map<String, Long> result2 = result1.stream()
                .collect(
                        Collectors.toMap(
                                Car::getName, Car::getPrice, // key = name, value = price
                                (oldValue, newValue) -> oldValue,       // if same key, take the old key
                                LinkedHashMap::new                      // returns a LinkedHashMap, keep order
                        ));

        System.out.println("Result 2 : " + result2);

    }
}


