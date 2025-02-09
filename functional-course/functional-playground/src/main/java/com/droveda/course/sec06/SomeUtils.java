package com.droveda.course.sec06;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SomeUtils {

    public static void main(String[] args) {
        long count = Stream.of(1, 2, 3, 4, 5)
                .count();

        System.out.println(count);

        Integer reduce = Stream.of(2, 3, 2, 2)
                .reduce(0, Integer::sum);
        System.out.println(reduce);

        System.out.println("---");

        int[] numberArr = {12, 3, 3, 5, 1};
        IntStream stream = Arrays.stream(numberArr);
        stream
                .forEach(System.out::println);

        System.out.println("---");

        OptionalInt min = IntStream.of(4, 3, 4, 5).min();
        System.out.println(min);

        int sum = IntStream.range(1, 10).sum();
        System.out.println(sum);

        System.out.println("---");

        int sum1 = IntStream.iterate(1, e -> e + 2).limit(10)
                .peek(System.out::println)
                .sum();

        System.out.println(sum1);

        List<Integer> list = IntStream.iterate(2, e -> e * 2)
                .limit(10)
                .boxed()
                .toList();
        System.out.println(list);

        long reduce1 = LongStream.rangeClosed(1, 50).reduce(1L, (x, y) -> x * y);
        System.out.println(reduce1);

        BigInteger reduce2 = LongStream.rangeClosed(1, 50).mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
        System.out.println(reduce2);

    }

}
