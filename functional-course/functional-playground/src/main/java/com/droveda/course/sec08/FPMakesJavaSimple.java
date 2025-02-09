package com.droveda.course.sec08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FPMakesJavaSimple {

    public static void main(String[] args) throws IOException {
        List<String> courses = List.of("Spring", "Spring boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        courses.stream().parallel()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        Files.lines(Paths.get("myfile.txt"))
                .map(str -> str.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        Files.list(Paths.get(".")).parallel()
                .filter(Files::isDirectory)
                .forEach(System.out::println);

    }

}
