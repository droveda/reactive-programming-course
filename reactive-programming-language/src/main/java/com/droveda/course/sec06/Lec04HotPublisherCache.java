package com.droveda.course.sec06;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    - publish().autoConnect(0) - will provide new values to the subscribers.
    - replay allows us to cache
 */
public class Lec04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {

        var stockFlux = stockStream()
                .replay(1).autoConnect(0);

        Util.sleepSeconds(4);

        log.info("sam joined");

        stockFlux
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(4);

        log.info("mike joined");

        stockFlux
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);
    }

    //stocks
    private static Flux<Integer> stockStream() {
        return Flux.<Integer>generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting price: {}", price));
    }

}
