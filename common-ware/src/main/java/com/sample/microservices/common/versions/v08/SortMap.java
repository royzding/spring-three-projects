package com.sample.microservices.common.versions.v08;

import java.util.*;
import java.util.stream.Collectors;

public class SortMap {

    //Sort a map by key
    public static void sortMapByKey() {

        List<Car> result0 = Car.getRandomCarList();

        System.out.println("Result 0 : " + result0);

        // key = id, value - car
        Map<Integer, Car> result1 = new HashMap<>();
        //result0.stream().collect(Collectors.toMap(Car::getId, Function.identity()));

        result0.forEach(x->result1.put(x.getId(), x));

        System.out.println("Result 1 : " + result1);

        // key = id, value - car, sorted
        Map<Integer, Car> result2 = new TreeMap<>(result1);

        System.out.println("Result 2 : " + result2);

        // key = id, value = car, sorted as reverse order
        Map<Integer, Car> result3 = new TreeMap<>(
                (Comparator<? super Integer>) (id1, id2) -> id2.compareTo(id1)
        );

        result3.putAll(result2);

        System.out.println("Result 3 : " + result3);

        List<Map.Entry<Integer, Car>> result4 = new LinkedList<Map.Entry<Integer, Car>>(result3.entrySet());

        //sort with Lambda Expression
        result4.sort((Map.Entry<Integer, Car> c1, Map.Entry<Integer, Car> c2) -> (int)(c1.getValue().getPrice() - (c2.getValue().getPrice())));

        System.out.println("Result 4 : " + result4);

        Map<Car, Integer> result5 = result4
                .stream()
                .collect(Collectors.toMap(Map.Entry<Integer, Car>::getValue, Map.Entry<Integer, Car>::getKey, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println("Result 5 : " + result5);

        //sort with Lambda Expression reverse
        result4.sort((Map.Entry<Integer, Car> c1, Map.Entry<Integer, Car> c2) -> (int)(c2.getValue().getPrice() - (c1.getValue().getPrice())));

        System.out.println("Result 4 : " + result4);

        Map<Car, Integer> result6 = result4
                .stream()
                .collect(Collectors.toMap(Map.Entry<Integer, Car>::getValue, Map.Entry<Integer, Car>::getKey, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println("Result 6 : " + result6);

    }

}


