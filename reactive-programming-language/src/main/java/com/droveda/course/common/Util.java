package com.droveda.course.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.UnaryOperator;

public class Util {

    private static final Logger log = LoggerFactory.getLogger(Util.class);

    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);

        Subscriber<Integer> subscriber = subscriber();
        mono.subscribe(subscriber);
        mono.subscribe(subscriber("sub2"));

        var monoS = Mono.just("Test 123");
        monoS.subscribe(subscriber("my-sub-username"));
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {

        return flux -> flux
                .doOnSubscribe(s -> log.info("subscribed to {}", name))
                .doOnCancel(() -> log.warn("cancelled for {}", name))
                .doOnComplete(() -> log.info("completed for {}", name));
    }


}
