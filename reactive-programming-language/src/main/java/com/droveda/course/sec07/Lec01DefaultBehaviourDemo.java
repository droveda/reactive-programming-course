package com.droveda.course.sec07;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
    By default, the current thread is doing all the work
 */
public class Lec01DefaultBehaviourDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviourDemo.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {

                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> log.info("value: {}", v));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("sub2"));

        new Thread(runnable).start();
        new Thread(runnable2).start();
    }

}
