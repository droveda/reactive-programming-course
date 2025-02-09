package com.droveda.course.sec05;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class FP04CustomClass {

    public static void main(String[] args) {

        var courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000)
        );


        //allMatch, noneMatch, anyMatch

        boolean result = courses.stream()
                .allMatch(c -> c.getReviewScore() > 95);
        System.out.println(result);


        boolean result2 = courses.stream()
                .noneMatch(c -> c.getReviewScore() < 90);
        System.out.println(result2);


        boolean result3 = courses.stream()
                .anyMatch(c -> c.getReviewScore() > 99);
        System.out.println(result3);

        Comparator<Course> comparingByNumberOfStudents = Comparator.comparing(Course::getNoOfStudents);
        Comparator<Course> comparingByNumberOfStudentsDesc = Comparator.comparing(Course::getNoOfStudents).reversed();
        List<Course> listResult = courses.stream()
                .sorted(comparingByNumberOfStudentsDesc)
                .toList();

        System.out.println(listResult);


        Comparator<Course> comparingByNumberOfStudentsAndReviewScore = Comparator.comparing(Course::getNoOfStudents)
                .thenComparing(Course::getReviewScore)
                .reversed();

        List<Course> listResult2 = courses.stream()
                .sorted(comparingByNumberOfStudentsAndReviewScore)
                .toList();

        System.out.println(listResult2);


        List<Course> listResult3 = courses.stream()
                .sorted(comparingByNumberOfStudentsAndReviewScore)
                .limit(5)
                .toList();

        System.out.println(listResult3);


        List<Course> listResult4 = courses.stream()
                .sorted(comparingByNumberOfStudentsAndReviewScore)
                .skip(3)
                .limit(5)
                .toList();

        System.out.println(listResult4);

        System.out.println(courses);

        List<Course> listA = courses.stream()
                .takeWhile(c -> c.getReviewScore() >= 95)
                .toList();
        System.out.println(listA);


        List<Course> listB = courses.stream()
                .dropWhile(c -> c.getReviewScore() >= 95)
                .toList();
        System.out.println(listB);

        Optional<Course> max = courses
                .stream()
                .max(comparingByNumberOfStudentsAndReviewScore);
        System.out.println(max.get());


        Optional<Course> min = courses
                .stream()
                .min(comparingByNumberOfStudentsAndReviewScore);
        System.out.println(min.get());

        Optional<Course> first = courses
                .stream()
                .filter(c -> c.getReviewScore() < 90)
                .findFirst();
        System.out.println(first);


        Optional<Course> any = courses
                .stream()
                .filter(c -> c.getReviewScore() > 95)
                .findAny();
        System.out.println(any);


        var num = courses
                .stream()
                .filter(c -> c.getReviewScore() > 95)
                .mapToInt(Course::getNoOfStudents)
                .sum();
        System.out.println(num);


        var average = courses
                .stream()
                .filter(c -> c.getReviewScore() > 95)
                .mapToInt(Course::getNoOfStudents)
                .average();
        System.out.println(average);

        var count = courses
                .stream()
                .filter(c -> c.getReviewScore() > 95)
                .count();
        System.out.println(count);

        var max2 = courses
                .stream()
                .filter(c -> c.getReviewScore() > 95)
                .mapToInt(Course::getNoOfStudents)
                .max();
        System.out.println(max2);

        Map<String, List<Course>> collect = courses
                .stream()
                .collect(Collectors.groupingBy(Course::getCategory));
        System.out.println(collect);


        Map<String, Long> collect2 = courses
                .stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting()));
        System.out.println(collect2);


        Map<String, Optional<Course>> collect3 = courses
                .stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.maxBy(Comparator.comparing(Course::getReviewScore))));
        System.out.println(collect3);

        Map<String, List<String>> collect4 = courses
                .stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.mapping(Course::getName, Collectors.toList())));
        System.out.println(collect4);
    }

}
