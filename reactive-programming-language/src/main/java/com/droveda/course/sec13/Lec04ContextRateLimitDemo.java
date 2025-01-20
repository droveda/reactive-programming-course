package com.droveda.course.sec13;

import com.droveda.course.common.Util;
import com.droveda.course.sec13.client.ExternalServiceClient;
import reactor.util.context.Context;

/*
    ensure that the external service is up and running!
 */
public class Lec04ContextRateLimitDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "mike"))
                    .subscribe(Util.subscriber());
            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(5);

    }

}
