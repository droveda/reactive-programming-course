package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
//        onErrorReturn();

//        onErrorResume();

//        onErrorComplete();


        onErrorContinue();

    }

    private static void onErrorReturn() {
        Mono.just(5)
//        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i) //intentional
//                .onErrorReturn(-1)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    private static void onErrorResume() {
        Mono.error(new RuntimeException("Oops"))
                .onErrorResume(ArithmeticException.class, e -> fallback1())
                .onErrorResume(e -> fallback2())
                .onErrorReturn(-5)
                .subscribe(Util.subscriber());
    }

    private static void onErrorComplete() {
        Mono.error(new RuntimeException("oops"))
                .onErrorComplete(RuntimeException.class)
                .subscribe(Util.subscriber());
    }

    private static void onErrorContinue() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i) //intentional
                .onErrorContinue((err, obj) -> {
                    log.error("Error: {}", obj, err);
                })
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 1000));
    }


}
