package com.droveda.course;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FP02Functional {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        int sum = addListFunctional(numbers);

        System.out.println(sum);

        Integer max = numbers.stream()
                .reduce(Integer.MIN_VALUE, (x, y) -> x > y ? x : y);
        System.out.println(max);

        Integer min = numbers.stream()
                .reduce(Integer.MAX_VALUE, (x, y) -> x < y ? x : y);
        System.out.println(min);

        Integer sumSquare = numbers.stream()
                .map(x -> x * x)
                .reduce(0, (x, y) -> x + y);

        System.out.println(sumSquare);


        Integer oodSum = numbers.stream()
                .filter(x -> x % 2 == 1)
                .reduce(0, (x, y) -> x + y);

        System.out.println(oodSum);

        numbers.stream()
                .distinct().forEach(System.out::println);

        System.out.println("-----");

        numbers.stream()
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println("-----");


        List<String> courses = List.of("Spring", "Spring boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        courses.stream()
                .sorted()
                .forEach(System.out::println);


        System.out.println("-----");

        courses.stream()
                .sorted(Comparator.comparing(String::length))
                .forEach(System.out::println);

        System.out.println("-----");

        List<Integer> collect = numbers.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());

        collect.stream()
                .forEach(System.out::println);

    }

    private static int addListFunctional(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);

    }

    private static int sum(int a, int b) {
        return a + b;
    }

}
