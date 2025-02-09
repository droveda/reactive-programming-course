package com.droveda.course;

import java.util.List;

public class FP02StreamOperations {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        numbers.stream()
                .distinct() // intermediate operation, it returns a stream
                .sorted() // intermediate operation, it returns a stream
                .forEach(System.out::println); // returns void, it is a terminal operation

    }

}
