package com.droveda.course.sec02;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 * creating publisher is a lightweight operation
 * Executing time-consuming business logic should be delayed
 */
public class Lec09PublisherCreateVsExecution {

    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);


    public static void main(String[] args) {

        getName()
                .subscribe(Util.subscriber());

    }

    private static Mono<String> getName() {
        log.info("entered the method");

        return Mono.fromSupplier(() -> {
            log.info("generating username");
            Util.sleepSeconds(3);
            return Util.faker().name().firstName();
        });
    }

}
