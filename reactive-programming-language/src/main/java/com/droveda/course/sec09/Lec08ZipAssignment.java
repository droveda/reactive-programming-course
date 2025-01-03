package com.droveda.course.sec09;

import com.droveda.course.common.Util;
import com.droveda.course.sec09.assignment.ExternalServiceClient;

public class Lec08ZipAssignment {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 1; i < 10; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(2);

    }

}
