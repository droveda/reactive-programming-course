package com.droveda.course.sec12;

import com.droveda.course.common.Util;
import reactor.core.publisher.Sinks;

public class Lec04Multicast {


    public static void main(String[] args) {
//        demo1();

        demo2();
    }

    private static void demo1() {

        var sink = Sinks.many().multicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you?");
        sink.tryEmitNext("???");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("jake"));

        sink.tryEmitNext("one more");

    }

    //warmup behaviour
    private static void demo2() {

        var sink = Sinks.many().multicast().onBackpressureBuffer();

        var flux = sink.asFlux();



        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you?");
        sink.tryEmitNext("???");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        flux.subscribe(Util.subscriber("jake"));

        sink.tryEmitNext("one more");

    }

}
