package com.sample.microservices.common.versions.v08;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;


public class ListOfListToList {

    public static List<List<String>> getListOfList() {

        List<String> list = Arrays.asList("12", "2A3B", "A1", "B2", "C3D4F5G");

        List<List<String>> collect = list.stream()
                .map(x -> new Scanner(x).findAll("\\D+")
                        .map(MatchResult::group)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

        return collect;
    }

    //list of list to list using flatMap
    public static List<String> listOfListToList(List<List<String>> lists) {

        List<String> collect = lists.stream().flatMap(List::stream).collect(Collectors.toList());

        collect.forEach(System.out::println);

        return collect;
    }

}


