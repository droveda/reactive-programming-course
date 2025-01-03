package com.droveda.course.sec12;

import com.droveda.course.common.Util;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {

    public static void main(String[] args) {
//        demo1();

        demo2();
    }

    private static void demo1() {

        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you?");
        sink.tryEmitNext("???");

        flux.subscribe(Util.subscriber("sam"));


    }

    private static void demo2() {

        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you?");
        sink.tryEmitNext("???");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));


    }

}
