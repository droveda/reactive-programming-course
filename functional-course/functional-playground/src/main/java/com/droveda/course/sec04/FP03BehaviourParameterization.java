package com.droveda.course.sec04;

import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;

public class FP03BehaviourParameterization {

    public static void main(String[] args) {
        var numbers = List.of(12, 9, 13, 4, 6, 2, 5, 12, 15);

        numbers.stream()
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);


        numbers.stream()
                .filter(x -> x % 2 != 0)
                .forEach(System.out::println);


        System.out.println("-----");

        filterAndPrint(x -> x % 2 == 0, numbers);
        filterAndPrint(x -> x % 2 != 0, numbers);

        System.out.println("-----");
        filterAndPrint(x -> x % 3 == 0, numbers);

        processNumbers(x -> x * x, numbers);
        processNumbers(x -> x * x * x, numbers);

        System.out.println("-----");

        Supplier<Integer> sup = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 10;
            }
        };

        Supplier<Integer> supRandom = () -> {
            Random random = new Random();
            return random.nextInt(1000);
        };
        System.out.println(supRandom.get());

        UnaryOperator<Integer> unary = (x) -> x * 3;
        System.out.println(unary.apply(10));

        BiPredicate<Integer, String> biPredicate = (number, string) -> {
            return number == 10 && string.equalsIgnoreCase("Droveda");
        };
        System.out.println(biPredicate.test(10, "Droveda"));
        System.out.println(biPredicate.test(10, "test"));

        BiFunction<Integer, String, String> bi = (number, string) -> {
            return number + " " + string;
        };
        System.out.println(bi.apply(10, "Droveda"));

        BiConsumer<String, String> biConsumer = (s1, s2) -> {
            System.out.println(s1 + " " + s2);
        };
        biConsumer.accept("Hello", "World!");

        Consumer<String> c = (str) -> System.out.println(str);
        c.accept("hello!");
    }

    private static void filterAndPrint(Predicate<Integer> predicate, List<Integer> numbers) {
        numbers.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }

    private static void processNumbers(Function<Integer, Integer> function, List<Integer> numbers) {

        List<Integer> result = numbers.stream()
                .map(function)
                .collect(Collectors.toList());
        System.out.println(result);

    }

}
