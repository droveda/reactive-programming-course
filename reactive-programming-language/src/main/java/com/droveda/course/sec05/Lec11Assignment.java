package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import com.droveda.course.sec05.assignment.ExternalServiceClient;

public class Lec11Assignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(3);

    }

}
