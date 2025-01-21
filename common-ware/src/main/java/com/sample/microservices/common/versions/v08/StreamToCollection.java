package com.sample.microservices.common.versions.v08;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamToCollection {

    public static void streamToCollection() {

        List<String> strList = List.of("1", "2", "3", "4", "5", "5", "6","6", "7");

        //stream to list
        System.out.println(strList.stream().collect(Collectors.toList()));

        //stream to set
        System.out.println(strList.stream().collect(Collectors.toSet()));

        //stream to array
        System.out.println(Arrays.toString(strList.stream().toArray(String[]::new)));

    }

}


