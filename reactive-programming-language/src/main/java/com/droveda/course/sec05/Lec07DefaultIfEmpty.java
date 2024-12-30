package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec07DefaultIfEmpty {

    public static void main(String[] args) {

        Mono.empty()
                .defaultIfEmpty("fallbackValue")
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .filter(i -> i > 11)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber());

    }

}
