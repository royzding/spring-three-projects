package com.sample.microservices.common.versions.v17;

import org.springframework.stereotype.Service;

@Service
public class PatternMatchingForSwitch {

    public static void main(String[] args) {
        new PatternMatchingForSwitch().getResults();
    }

    public void getResults() {

        System.out.println("Integer to weekday string: " +  getDayOfWeek(1) );
        System.out.println("Integer to weekday string: " +  getDayOfWeek(2) );
        System.out.println("Integer to weekday string: " +  getDayOfWeek(3) );
        System.out.println("Integer to weekday string: " +  getDayOfWeek(4) );
        System.out.println("Integer to weekday string: " +  getDayOfWeek(5) );
        System.out.println("Integer to weekday string: " +  getDayOfWeek(6) );
        System.out.println("Integer to weekday string: " +  getDayOfWeek(7) );
        System.out.println("Integer to weekday string: " +  getDayOfWeek(8) );

    }
    public String getDayOfWeek(int day) {

        return switch (day) {

            case 1 -> "Monday";

            case 2 -> "Tuesday";

            case 3 -> "Wednesday";

            case 4 -> "Thursday";

            case 5 -> "Friday";

            case 6 -> "Saturday";

            case 7 -> "Sunday";

            default -> "Unknown";

        };

    }

}
