package com.droveda.course.sec07;

import com.droveda.course.sec05.Course;

import java.util.List;
import java.util.function.Predicate;

public class HighOrderFunctions {

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


        Predicate<Course> pred1 = createPredicateWithCutoffScore(95);
        Predicate<Course> pred2 = createPredicateWithCutoffScore(90);

    }

    //This is a High Order Function
    private static Predicate<Course> createPredicateWithCutoffScore(int cutoffReviewStore) {
        return course -> course.getReviewScore() > cutoffReviewStore;
    }

}
