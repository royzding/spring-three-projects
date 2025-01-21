package com.sample.microservices.common.versions.v08;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class CollectionToStream {

    public static void listToStream() {

        List<String> list = List.of("a", "b", "c", "d", "e", "f");

        Stream<String> listStream = list.stream();

        System.out.println(listStream.toList());

    }

    public static void setToStream() {

        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e", "f"));

        Stream<String> setStream = set.stream();

        System.out.println(setStream.toList());

    }

    public static void mapToStream() {

        Map<Integer, String> map = Map.of(1, "One", 2, "Two", 3, "Three", 4, "Four", 5, "Five");

        //map to key stream.
        Stream<Integer> ketStream = map.keySet().stream();

        //map to Value stream.
        Stream<String> valueStream = map.values().stream();

        //map to entry stream.
        Stream<Map.Entry<Integer, String>> entryStream = map.entrySet().stream();

    }

    public static void arrayToStream() {

        //convert Primitive Array to Stream

        int[] intArray = {1, 2, 3, 4, 5, 6};

        // 1. Arrays.stream -> IntStream
        IntStream intStream1 = Arrays.stream(intArray);

        String strJoining1 = intStream1.mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining1);

        // 2. Stream.of -> Stream<int[]>
        Stream<int[]> intStream2 = Stream.of(intArray);

        // Cant print Stream<int[]> directly, convert / flat it to IntStream
        IntStream intStream3 = intStream2.flatMapToInt(Arrays::stream);

        String strJoining2 = intStream3.mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining2);

        //convert Object Array to Stream

        Integer[] integerArray = {1, 2, 3, 4, 5, 6};

        // 1. Arrays.stream -> Stream<Integer>
        Stream<Integer> integerStream1 = Arrays.stream(integerArray);

        String strJoining3 = integerStream1.map(String::valueOf).collect(Collectors.joining(", "));

        System.out.println(strJoining3);

        // 2. Stream.of -> Stream<Integer>
        Stream<Integer> integerStream2 = Stream.of(integerArray);

        String strJoining4 = integerStream2.map(String::valueOf).collect(Collectors.joining(", "));

        System.out.println(strJoining4);

        String[] array = {"a", "b", "c", "d", "e", "f"};

        //Arrays.stream
        Stream<String> stream1 = Arrays.stream(array);
        System.out.println(stream1.toList());

        //Stream.of
        Stream<String> stream2 = Stream.of(array);
        System.out.println(stream2.toList());

        //2d array
        String[][] strArray = new String[][]{{"1", "2"}, {"3", "4"}};

        Stream<String[]> strArrayStream = Arrays.stream(strArray);

        String strJoining5 = strArrayStream
                .flatMap(Arrays::stream)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining5);

    }

}


