package com.droveda.course.sec04;

import java.util.List;
import java.util.function.Supplier;

public class FP03MethodReferences {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "CPF", "Azure", "Docker", "Kubernetes");

        courses.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);


        courses.stream()
                .map(str -> str.toUpperCase())
                .forEach(FP03MethodReferences::print);

        Supplier<String> supplier = String::new;
        System.out.println(supplier.get());

    }

    private static void print(String str) {
        System.out.println(str);
    }

}
