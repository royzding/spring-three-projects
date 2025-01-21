package com.sample.microservices.common.versions.v08;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class ArrayToStream {

    public static void arrayToStream() {

        String[] array = {"a", "b", "c", "d", "e", "f"};

        //Arrays.stream
        Stream<String> stream1 = Arrays.stream(array);
        System.out.println(stream1.toList());

        //Stream.of
        Stream<String> stream2 = Stream.of(array);
        System.out.println(stream2.toList());

        int[] intArray = {1, 2, 3, 4, 5, 6};

        // 1. Arrays.stream -> IntStream
        IntStream intStream1 = Arrays.stream(intArray);

        String strJoining1 = intStream1.mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining1);

        // 2. Stream.of -> Stream<int[]>
        Stream<int[]> temp = Stream.of(intArray);

        // Cant print Stream<int[]> directly, convert / flat it to IntStream
        IntStream intStream2 = temp.flatMapToInt(Arrays::stream);

        String strJoining2 = intStream2.mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining2);

        //2d array
        String[][] strArray = new String[][]{{"1", "2"}, {"3", "4"}};

        Stream<String[]> strArrayStream = Arrays.stream(strArray);

        String strJoining3 = strArrayStream
                .flatMap(Arrays::stream)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining3);

    }

}


