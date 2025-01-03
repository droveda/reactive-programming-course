package com.droveda.course.sec07;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06EvenLoopIssueFix {

    private static final Logger log = LoggerFactory.getLogger(Lec06EvenLoopIssueFix.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        log.info("Starting...");

        for (int i = 1; i <= 5; i++) {

            client.getProductName(i)
                    .map(Lec06EvenLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(20);

    }

    private static String process(String input) {
        Util.sleepSeconds(2);
        return input + "-processed";
    }
}
