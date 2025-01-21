package com.sample.microservices.common.versions.v08;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsJoining {

    public static void collectorsJoining() {

        List<String> list = Arrays.asList("1", "2", "3", "4");

        String strJoining1 = list.stream().collect(Collectors.joining(", "));

        System.out.println(strJoining1);

        String strJoining2 = String.join(", ", list);

        System.out.println(strJoining2);

        int[] intArray = {1, 2, 3, 4};

        String strJoining3 = Arrays.stream(intArray)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining3);

        //2d array
        String[][] strArray = new String[][]{{"1", "2"}, {"3", "4"}};

        String strJoining4 = Arrays.stream(strArray)
                .flatMap(Arrays::stream)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining4);

        int[][] intArrays = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8, 9}};

        String strJoining5 = Arrays.stream(intArrays).flatMapToInt(Arrays::stream)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));

        System.out.println(strJoining5);

    }

}
