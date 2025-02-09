package com.droveda.course.sec04;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FP03FunctionalInterfaces {

    /*
      boolean isEven(int x) {
        return x % 2 == 0;
      }

      int squared(int x) {
        return x * x;
      }
     */

    public static void main(String[] args) {
        var numbers = List.of(12, 9, 13, 4, 6, 2, 5, 12, 15);

        Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;
        Predicate<Integer> isEvenPredicate2 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        };


        Function<Integer, Integer> squareFunction = x -> x * x;
        Function<Integer, Integer> squareFunction2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * x;
            }
        };


        Consumer<Integer> sysOutConsumer = System.out::println;
        Consumer<Integer> sysOutConsumer2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.println(x);
            }
        };

        numbers.stream()
                .filter(isEvenPredicate2)
                .map(squareFunction2)
                .forEach(sysOutConsumer2);


        System.out.println("-----");
        System.out.println("-----");

        BinaryOperator<Integer> sum1 = Integer::sum;

        BinaryOperator<Integer> binaryOperator = new BinaryOperator<>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };

        Integer sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum);

        System.out.println("-----");
        System.out.println("-----");


        Integer sum2 = numbers.stream()
                .reduce(0, binaryOperator);
        System.out.println(sum2);
    }

}
