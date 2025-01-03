package com.droveda.course.sec09;

import com.droveda.course.common.Util;
import com.droveda.course.sec09.assignment.ExternalServiceClient;
import reactor.core.publisher.Flux;

public class Lec12FluxFlatMapAssignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        Flux.range(1, 10)
                .flatMap(client::getProduct)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);

    }

}
