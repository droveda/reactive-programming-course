package com.droveda.course;

import java.util.List;

public class FP01Exercises {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 5, 12, 15);

        printOddNumbersInListFunctional(numbers);

        List<String> courses = List.of("Spring", "Spring boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        courses.stream()
//                .filter(course -> course.contains("Spring"))
                .filter(course -> course.length() >= 4)
                .forEach(System.out::println);

        printSquaresOfEvenNumbersInListFunctional(numbers);
        printQubesOfEvenNumbersInListFunctional(numbers);

        System.out.println("_____________________________");
        courses.stream()
                .map(String::length)
                .forEach(System.out::println);

    }

    private static void printOddNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> number % 2 != 0)
                .forEach(System.out::println);
    }

    private static void printSquaresOfEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(n -> n * n)
                .forEach(System.out::println);
    }

    private static void printQubesOfEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(n -> n * n * n)
                .forEach(System.out::println);
    }

}
