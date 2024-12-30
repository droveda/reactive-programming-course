package com.droveda.course.sec04;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {

//        simpleGenerate();
        generateCountries();


    }

    private static void simpleGenerate() {
        Flux.generate(synchronousSink -> {
                    log.info("invoked");
                    synchronousSink.next(1);
                    // synchronousSink.next(2);
//            synchronousSink.complete();
                })
                .take(4)
                .subscribe(Util.subscriber());
    }

    private static void generateCountries() {
        Flux.<String>generate(sync -> {
                    var name = Util.faker().country().name();
                    log.info("invoked: {}", name);
                    sync.next(name);
                })
                .takeUntil(s -> s.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }


}
