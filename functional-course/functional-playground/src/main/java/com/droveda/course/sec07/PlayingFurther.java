package com.droveda.course.sec07;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayingFurther {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        List<String> courses2 = List.of("Spring", "Spring boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        String collect = courses.stream()
                .collect(Collectors.joining(","));
        System.out.println(collect);

        List<String> listR = courses.stream()
                .map(course -> course.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println(listR);

        List<List<String>> collect1 = courses.stream()
                .flatMap(course -> courses2.stream().map(course2 -> List.of(course, course2)))
                .collect(Collectors.toList());
        System.out.println(collect1);


        List<List<String>> collect2 = courses.stream()
                .flatMap(course -> courses2.stream().map(course2 -> List.of(course, course2)))
                .filter(list -> !list.get(0).equals(list.get(1)))
                .collect(Collectors.toList());
        System.out.println(collect2);


        List<List<String>> collect3 = courses.stream()
                .flatMap(course -> courses2.stream()
                        .filter(course2 -> course2.length() == course.length())
                        .map(course2 -> List.of(course, course2)))
                .filter(list -> !list.get(0).equals(list.get(1)))
                .collect(Collectors.toList());
        System.out.println(collect3);

        Optional<String> first = courses.stream()
                .peek(System.out::println)
                .filter(c -> c.length() > 11)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .findFirst();
        System.out.println(first);


    }

}
