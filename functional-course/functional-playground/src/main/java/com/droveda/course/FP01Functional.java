package com.droveda.course;

import java.util.List;

public class FP01Functional {

    public static void main(String[] args) {
        printNumbersInListFunctional(List.of(12, 9, 13, 4, 6, 2, 5, 12, 15));
    }

    private static void printNumbersInListFunctional(List<Integer> numbers) {

        numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * 10)
                .forEach(System.out::println);


    }

}
