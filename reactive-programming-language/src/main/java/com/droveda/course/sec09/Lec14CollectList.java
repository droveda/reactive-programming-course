package com.droveda.course.sec09;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec14CollectList {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .concatWith(Mono.error(new RuntimeException("Crash")))
                .collectList()
                .subscribe(Util.subscriber());

    }

}
