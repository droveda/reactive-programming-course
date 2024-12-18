package com.droveda.course.sec03;

import com.droveda.course.common.Util;
import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .subscribe(Util.subscriber());

        //assignment - generate 1- random first names
        Flux.range(1, 10)
                .map(i -> i + " - " + Faker.instance().name().firstName())
                .subscribe(Util.subscriber());

    }

}
