package com.droveda.course.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        var mono = Mono.just(1)
                //.map( i ->i / 0) // this is a test to call onError
                .map(i -> i + "a");

        mono.subscribe(
                i -> log.info("received: {}", i),
                err -> log.error("error", err), // this one is optional
                () -> log.info("completed!"), // this one is optional
                subscription -> subscription.request(1) //this one is optional, by default it will call request
        );

        // just my test
        test2();
    }

    private static void test2() {
        var a = new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info(s);
            }
        };
        var c = a.andThen(b -> {
            log.info(b + " | hello!");
        });

        var mono = Mono.just("AAA 123");

        mono.subscribe(c);
    }

}
