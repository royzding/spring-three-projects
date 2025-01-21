package com.sample.microservices.common.versions.v08;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ForEachLoop {

    public static void main(String[] args) throws Exception {
        loopAMap();
        loopAList();
        ForEachConsumer();
        ForEachWithOrder();
    }

    public static void loopAMap() {

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.put(null, 40);
        map.put("E", null);
        map.put("F", 60);

        map.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));

        System.out.println("filter out key as null");

        map.forEach(
                (k, v) -> {
                    // yes, we can put logic here
                    if (k != null){
                        System.out.println("Key : " + k + ", Value : " + v);
                    }
                }
        );
    }

    public static void loopAList() {

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add(null);
        list.add("D");
        list.add("E");

        // lambda
        // list.forEach(x -> System.out.println(x));

        // method reference
        list.forEach(System.out::println);

        // filter null value
        list.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    public static void ForEachConsumer() {

        List<String> list = Arrays.asList("1", "2", "3");
        Stream<String> stream = Stream.of("10", "20", "30");

        // convert a String to a int
        Consumer<String> doubleTheValue = (String x) -> {
            System.out.println( 2 * Integer.parseInt(x) );
        };

        // pass a Consumer
        list.forEach(doubleTheValue);

        stream.forEach(doubleTheValue);
    }

    public static void ForEachWithOrder() {

        Stream<String> s1 = Stream.of("1", "2", "3", "4", "5", "6");
        s1.forEach(System.out::println);

        Stream<String> s2 = Stream.of("1", "2", "3", "4", "5", "6");
        s2.parallel().forEach(x -> System.out.println(x));

        Stream<String> so1 = Stream.of("1", "2", "3", "4", "5", "6");
        so1.forEachOrdered(System.out::println);

        Stream<String> so2 = Stream.of("1", "2", "3", "4", "5", "6");
        so2.parallel().forEachOrdered(x -> System.out.println(x));

    }
}
