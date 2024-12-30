package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09Timeout {

    public static void main(String[] args) {

        getProductName()
                .timeout(Duration.ofSeconds(1), fallback())
                .onErrorReturn("fallback")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(3));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(900));
    }

}
