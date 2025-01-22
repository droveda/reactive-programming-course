package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class Lec12AsExample {

    private static final Logger log = LoggerFactory.getLogger(Lec12AsExample.class);

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        flux
                .as(f -> Mono.just(Objects.requireNonNull(f.reduce(0, Integer::sum).block())))
                .subscribe(Util.subscriber());


//        Flux<String> map = flux.map(i -> i + "aaa");
//        map.subscribe(Util.subscriber());


    }

}
