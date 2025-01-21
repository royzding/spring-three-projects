package com.sample.microservices.common.versions.v11;

/*
    One of the most significant changes introduced in Java 11 is the ability
    to use the var keyword in lambda expressions.
    This feature allows you to declare lambda parameters using var
    instead of explicitly declaring their type.
 */

public class NewStringMethods {

    public static void main(String[] args) throws Exception {

        //isBlank(). It checks whether a string is empty or contains only white spaces.

        String str1 = "";
        String str2 = "  ";
        String str3 = "Hello World";
        System.out.println(str1.isBlank()); // Output: true
        System.out.println(str2.isBlank()); // Output: true
        System.out.println(str3.isBlank()); // Output: false

        //strip(). It removes white spaces from both the beginning and the end of a string.
        //This method is similar to the trim() method, but it is more powerful
        //because it can handle Unicode white spaces.

        String str4 = "  Hello World  ";
        String str5 = "\u2005Hello World\u2005";
        System.out.println(str4.strip()); // Output: "Hello World"
        System.out.println(str5.strip()); // Output: "Hello World"

    }

}
