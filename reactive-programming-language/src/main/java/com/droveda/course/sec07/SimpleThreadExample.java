package com.droveda.course.sec07;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleThreadExample {

    private static final Logger log = LoggerFactory.getLogger(SimpleThreadExample.class);

    public static void main(String[] args) {

        log.info("Init");

        var thread1 = new Thread(() -> {
            log.info("Doing some intensive task 1...");
            Util.sleepSeconds(4);
            log.info("Finished intensive task 1...");
        });

        var thread2 = new Thread(() -> {
            log.info("Doing some intensive task 2...");
            Util.sleepSeconds(2);
            log.info("Finished intensive task 2...");
        });

        thread1.start();
        thread2.start();


        log.info("Ending...");

    }

}
