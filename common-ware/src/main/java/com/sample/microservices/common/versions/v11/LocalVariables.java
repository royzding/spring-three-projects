package com.sample.microservices.common.versions.v11;

import java.util.Arrays;
import java.util.List;

/*
    One of the most significant changes introduced in Java 11 is the ability
    to use the var keyword in lambda expressions.
    This feature allows you to declare lambda parameters using var
    instead of explicitly declaring their type.
 */

public class LocalVariables {

    public static void main(String[] args) throws Exception {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        //before 11
        numbers.forEach((Integer number) -> {
            System.out.println(number);
        });

        //start from 11
        numbers.forEach((var number) -> {
            System.out.println(number);
        });

    }

}
