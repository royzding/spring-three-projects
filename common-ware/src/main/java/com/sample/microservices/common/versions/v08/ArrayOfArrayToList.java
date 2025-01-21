package com.sample.microservices.common.versions.v08;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class ArrayOfArrayToList {

    //Array of Array to list using flatMap
    public static List<String> arrayOfArrayToList() {

        String[][] array = new String[][]{{"0", "1"}, {"2", "5"}, {"3", "8"}, {"10", "11"}, {"10", "8"}};

        String[] collect1 = Stream.of(array)
                .flatMap(Stream::of)
                .filter(x-> (Integer.parseInt(x) %2 == 0))
                .toArray(String[]::new);

        System.out.println(Arrays.toString(collect1));

        Set<String> collect2 = Stream.of(array)
                .flatMap(Stream::of)
                .filter(x-> (Integer.parseInt(x) %2 == 0))
                .collect(Collectors.toSet());

        System.out.println(collect2);

        List<String> collect3 = Stream.of(array)
                .flatMap(Stream::of)
                .filter(x-> (Integer.parseInt(x) %2 == 0))
                .toList();

        System.out.println(collect3);

        return collect3;
    }

    //Array of PrimitiveArray to list using flatMapToX
    public static void arrayOfPrimitiveArrayToList() {

        int[][] array0 = new int[][]{{0, 1}, {2, 5}, {3, 8}, {10, 11}, {10, 8}};

        int[] collect1 = Stream.of(array0)
                .flatMapToInt(Arrays::stream)
                .filter(x-> (x %2 == 0))
                .toArray();

        System.out.println(Arrays.toString(collect1));

        int[] array = {1, 2, 3, 4, 5, 6};

        //Stream<int[]>
        Stream<int[]> streamArray = Stream.of(array);

        //Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(Arrays::stream);

        intStream.forEach(System.out::println);    }
}


