package com.droveda.course.sec12;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {

    private static final Logger log = LoggerFactory.getLogger(Lec01SinkOne.class);

    public static void main(String[] args) {

//       demo1();

//        demo2();

        demo3();
    }

    private static void demo1() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber());
//        sink.tryEmitValue("hi");

//        sink.tryEmitEmpty();

        sink.tryEmitError(new RuntimeException("oops"));
    }

    private static void demo2() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();
        sink.tryEmitValue("hi");

        mono.subscribe(Util.subscriber("sam"));
        mono.subscribe(Util.subscriber("mike"));


    }


    private static void demo3() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("sam"));

        sink.emitValue("hi", (signalType, emitResult) -> {
            log.info("hi");

            log.info("Signal Type: {}", signalType.name());
            log.info("emitResult: {}", emitResult.name());

            return false;
        });

        sink.emitValue("hello", (signalType, emitResult) -> {
            log.info("hello");

            log.info("Signal Type: {}", signalType.name());
            log.info("emitResult: {}", emitResult.name());

            return false;
        });



    }
}
