package com.droveda.course.genericsstudy;

import java.lang.reflect.Constructor;

public class ReflectionTest {

    public static void main(String[] args) {

        Class<Person> personClass = Person.class;
        System.out.println(personClass);

        try {
            Class<?> aClass = Class.forName(Person.class.getName());

            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
            Person o = (Person) declaredConstructor.newInstance("John");

            System.out.println(o.getName());


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
