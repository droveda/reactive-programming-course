package com.droveda.course.sec11;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {

    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
//        demo1();

//        demo2();

        demo3();

        Util.sleepSeconds(10);
    }

    private static void demo1() {
        getCountryName()
                .retry()
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(2))
                                .doBeforeRetry(rs -> log.info("retrying: {}", rs.totalRetriesInARow()))
                )
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(2))
                                .filter(ex -> ex instanceof RuntimeException)
                                .onRetryExhaustedThrow((rs, ex) -> ex.failure())
                )
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);

        return Mono.fromSupplier(() -> {
                    if (atomicInteger.incrementAndGet() < 5) {
                        throw new RuntimeException("oops");
                    }
                    return Util.faker().country().name();
                })
                .doOnError(err -> log.info("error: {}", err.getMessage()))
                .doOnSubscribe(s -> log.info("subscribed"));
    }

}
