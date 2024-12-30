package com.droveda.course.sec04;

import com.droveda.course.common.Util;
import com.droveda.course.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
    Flux create does NOT check the downstream demand by default! It is by design!
 */
public class Lec04FluxCreateDownstreamDemand {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {
//        produceEarly();
        produceOnDemand();
    }

    private static void produceEarly() {
        var subscriber = new SubscriberImpl();

        Flux.<String>create(fluxsink -> {

            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("generated: {}", name);
                fluxsink.next(name);
            }

            fluxsink.complete();
        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();

        subscriber.getSubscription().request(2);
    }

    private static void produceOnDemand() {
        var subscriber = new SubscriberImpl();

        Flux.<String>create(fluxsink -> {
            fluxsink.onRequest(request -> {
                for (int i = 0; i < request && !fluxsink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    log.info("generated: {}", name);
                    fluxsink.next(name);
                }
            });

        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);

        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();

        subscriber.getSubscription().request(2);
    }

}
