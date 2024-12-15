package com.droveda.course.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Lec01LazyStream {

    private static final Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);

    public static void main(String[] args) {

        // if we do not have the terminal operator, the stream operators will not execute
        Stream.of(1)
                .peek(i -> log.info("received : {}", i))
                .toList();

    }

}
