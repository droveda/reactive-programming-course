package com.droveda.course.sec06;

import com.droveda.course.common.Util;
import com.droveda.course.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec06FluxCreateIssueFix {

    public static void main(String[] args) {
        var generator = new NameGenerator();
        var flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));


        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }

}
