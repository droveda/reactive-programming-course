package com.droveda.course.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Util {

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
        monoS.subscribe(subscriber("my-sub-name"));
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


}
