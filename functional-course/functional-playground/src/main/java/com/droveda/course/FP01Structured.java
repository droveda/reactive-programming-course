package com.droveda.course;

import java.util.List;

public class FP01Structured {

    public static void main(String[] args) {
        printAllNumbersInListStructured(List.of(12, 9, 13, 4, 6, 2, 5, 12, 15));
    }

    private static void printAllNumbersInListStructured(List<Integer> numbers) {

        for (Integer number : numbers) {
            System.out.println(number);
        }

    }

}
