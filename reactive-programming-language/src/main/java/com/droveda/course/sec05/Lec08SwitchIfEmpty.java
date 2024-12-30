package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {

    public static void main(String[] args) {

        /*
        real use case => postgres + redis
         */

        Flux.range(1, 10)
                .filter(i -> i > 11)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 3);
    }


}
