package com.droveda.course.sec10;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    collect items based on a given interval / size
 */
public class Lec01Buffer {

    public static void main(String[] args) {
//        demo1();
//        demo2();

//        demo3();

        demo4();

        Util.sleepSeconds(60);
    }

    private static void demo1() {
        eventStream()
                .buffer() //int-max value of the source has to complete
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        eventStream()
                .buffer(3) //every 3 items
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        eventStream()
                .buffer(Duration.ofMillis(1000)) //every 1000 ms
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
//                .take(10)
//                .concatWith(Flux.never())
                .map(i -> "event-" + (i + 1));
    }

}
