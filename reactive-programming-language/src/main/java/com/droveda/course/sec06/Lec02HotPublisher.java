package com.droveda.course.sec06;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    Hot - 1 data producer for all the subscribers
    share => publish().refCount(1)
    It needs 1 min subscriber to emit data.
    It stops when there is 0 subscriber.
    Re-subscription - It starts again where there is a new subscriber.
    To have min 2 subscribers, use publish().refCount(2)
 */
public class Lec02HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {

        var movieFlux = movieStream()
//                .publish().refCount(1);
                .share();

        Util.sleepSeconds(2);

        movieFlux
                .take(4)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);

        movieFlux
                .take(3)
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);
    }

    //movie theatre
    private static Flux<String> movieStream() {
        return Flux.generate(() -> {
                            log.info("received the request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "movie scene " + state;
                            log.info("Playing {}", scene);
                            sink.next(scene);
                            return ++state;
                        }).take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }

}
