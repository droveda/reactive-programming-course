package com.droveda.course.sec03;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {

    public static void main(String[] args) {

        Flux.just(1,  2, 3, "sam", "mike")
                .subscribe(Util.subscriber());

        Flux.range(3, 5)
                .map(i -> i / (i-4))
                .subscribe(Util.subscriber());

    }

}
