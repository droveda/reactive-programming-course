package com.droveda.course.sec08;

import java.util.stream.IntStream;

public class FPThreadsExample {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            IntStream.range(0, 10000).forEach(
                    i -> System.out.println(Thread.currentThread().getId() + " : " + i)
            );
        };

        Thread t = new Thread(runnable);
        t.start();

        Thread t2 = new Thread(runnable);
        t2.start();

        Thread t3 = new Thread(runnable);
        t3.start();
    }

}
