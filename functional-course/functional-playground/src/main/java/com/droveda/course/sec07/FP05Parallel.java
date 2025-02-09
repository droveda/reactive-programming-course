package com.droveda.course.sec07;

import java.util.stream.LongStream;

public class FP05Parallel {

    public static void main(String[] args) {
        //0, 1000000000

        long time = System.currentTimeMillis();

//        long sum = LongStream.range(0, 1000000000L).sum();
        long sum = LongStream.range(0, 1000000000L).parallel().sum();
        System.out.println(sum);

        System.out.println(System.currentTimeMillis() - time);
    }

}
