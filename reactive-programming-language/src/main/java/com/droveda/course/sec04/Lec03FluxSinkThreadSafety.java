package com.droveda.course.sec04;

import com.droveda.course.common.Util;
import com.droveda.course.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    public static void main(String[] args) {
//        demo1(); //this is not thread safe

        demo2(); //this is thread safe
    }

    private static void demo1() {
        var list = new ArrayList<Integer>();

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

    // arraylist is not thread safe.
    // flux sink is thread safe. we get all the 10000 items safely into array list
    private static void demo2() {
        var list = new ArrayList<String>();

        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

}
