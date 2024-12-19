package com.droveda.course.sec04;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    // to create a flux & emit items programmatically
    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            String country;

            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("canada"));
            fluxSink.complete();

        }).subscribe(Util.subscriber());

    }

}
