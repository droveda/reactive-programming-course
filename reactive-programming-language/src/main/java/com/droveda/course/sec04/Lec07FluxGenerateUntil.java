package com.droveda.course.sec04;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {

    public static void main(String[] args) {
//        demo1();
        
        demo2();
    }

    private static void demo1() {
        Flux.generate(sync -> {
            var country = Util.faker().country().name();
            sync.next(country);

            if (country.equalsIgnoreCase("canada")) {
                sync.complete();
            }
        }).subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.<String>generate(sync -> {
                    var country = Util.faker().country().name();
                    sync.next(country);
                })
                .takeUntil(s -> s.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

}
