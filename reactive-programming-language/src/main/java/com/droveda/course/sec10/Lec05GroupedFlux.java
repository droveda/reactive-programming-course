package com.droveda.course.sec10;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec05GroupedFlux {

    private static final Logger log = LoggerFactory.getLogger(Lec05GroupedFlux.class);

    public static void main(String[] args) {

        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i % 2)
                .flatMap(Lec05GroupedFlux::processEvents)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(60);

    }

    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> flux) {
        log.info("received flux for {}", flux.key());

        return flux
                .doOnNext(i -> log.info("key: {}, item: {}", flux.key(), i))
                .doOnComplete(() -> log.info("{} completed", flux.key()))
                .then();


    }

}
