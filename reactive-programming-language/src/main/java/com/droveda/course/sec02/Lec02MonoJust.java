package com.droveda.course.sec02;

import com.droveda.course.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String[] args) {
        var mono = Mono.just("wins");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        subscriber.getSubscription().request(10);
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();


        save(Mono.just("teste 1"));
    }

    private static void save(Publisher<String> publisher) {

    }

}
