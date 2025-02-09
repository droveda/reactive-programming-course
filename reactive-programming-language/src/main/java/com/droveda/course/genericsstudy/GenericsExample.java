package com.droveda.course.genericsstudy;

public class GenericsExample {

    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }

    public <T> void doSomething(T some) {
        System.out.println(some);
    }

    public <T extends Number> void printDouble(T number) {
        System.out.println(number.doubleValue());
    }

}
