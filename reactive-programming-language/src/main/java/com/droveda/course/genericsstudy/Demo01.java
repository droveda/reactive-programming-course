package com.droveda.course.genericsstudy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 1. https://medium.com/@aqilzeka99/mastering-generics-in-java-interview-questions-571232c02af9
    Type parameters in Generics
    The type parameters naming conventions are important to learn generics thoroughly. The common type parameters are as follows:
    T - Type
    E - Element
    K - Key
    N - Number
    V - Value
 */
public class Demo01 {

    public static void main(String[] args) {

        demo01();

        demo02();

        demo03();

        demo04();

    }

    private static void demo01() {

        List list = new ArrayList();
        list.add("Hello");
        String s = (String) list.get(0); //must cas to String

        List<String> list2 = new ArrayList<>();
        list2.add("Hello");
        var hello = list2.get(0); //No casting needed, type safety is ensured
        System.out.println(hello);

    }

    private static void demo02() {
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello 123");
        System.out.println(stringBox.getItem());
    }

    private static void demo03() {

        var genericsExample = new GenericsExample();
        Integer[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] names = {"Sam", "Mike", "Tom", "John"};

        genericsExample.printArray(intArr);
        genericsExample.printArray(names);

        genericsExample.doSomething("Something");
        genericsExample.doSomething(123);

        genericsExample.printDouble(5);
        genericsExample.printDouble(5.5);
//        genericsExample.printDouble("hello"); // compile-time error, String is not a subclass of Number

    }

    private static void demo04() {
        // wild cards ?

        // 1. Unbounded Wildcards (<?>) Accepts any type.
        List<?> list = new ArrayList<String>();
        list = new ArrayList<Integer>(); //Works with any type

        // 2. Upper-bounded Wildcard (<? extends Type>): Accepts a type or any subtype
        //<? extends T> allows any type that is a subtype of T and is used for read-only operations (you can’t add elements safely).
        printNumbers(List.of(1, 1.2, 100L, -2, Integer.parseInt("111"), new BigDecimal("123.45")));

        // 3. Lower-Bounded Wildcard (<? super Type>): Accepts a type or any supertype.
        //<? super T> allows any type that is a supertype of T and is used for write-only operations (you can add elements safely, but reading may give Object).
        List<Number> numbers = new ArrayList<>();
        addNumbers(numbers);
        addNumbers(numbers);
        System.out.println(numbers);

        List<Person> persons = new ArrayList<>();
        addPerson(persons);
        System.out.println(persons);

        readItems(persons);

        List<?> a = new ArrayList<>();

        printNumbers2(List.of("aa", 123, new Person("aa"), new HighPerson("bb"), new BigDecimal(123)));

    }

    private static void printNumbers(List<? extends Number> list) {
        for (Number n : list) {
            System.out.println(n.intValue());
        }

        Number number = list.get(0);
    }

    private static void printNumbers2(List<?> list) {
        for (Object n : list) {
            System.out.println(n);
        }

        Object o = list.get(0);
    }

    private static void addNumbers(List<? super Integer> list) {
        list.add(10); // You can add Integer or its subclass
    }

    private static void addPerson(List<? super Person> list) {
        System.out.println("addPerson -->");

        list.add(new Person("Mike")); // You can add Person or its subclass

        var high = new HighPerson("Tom");
        high.setHeight(180);
        list.add(high);

        Object o = list.get(0);

        for (Object p : list) {
            System.out.println(p);
        }
    }

    private static void readItems(List<?> myList) {
        System.out.println("---");
        myList.forEach(System.out::println);
    }

}
