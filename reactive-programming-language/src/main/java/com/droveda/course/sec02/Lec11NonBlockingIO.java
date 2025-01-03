package com.droveda.course.sec02;

import com.droveda.course.common.Util;
import com.droveda.course.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * to demo non-blocking IO
 * Ensure that the external service is up and running
 */
public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        log.info("Starting...");

        for (int i = 1; i <= 100; i++) {

            //why we should not use BLOCK
//            var username = client.getProductName(i).block(); //this line will block the execution, it's useful for unit-testing but this should be avoided

            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        //we need to wait because it is a non-blocking call
        Util.sleepSeconds(2);

    }

}
