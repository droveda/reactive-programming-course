package com.droveda.course.sec09.helper;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private final List<String> redis = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(sink -> {
                    log.info("generating username");
                    Util.sleepSeconds(1);
                    var name = Util.faker().name().firstName();
                    redis.add(name);
                    sink.next(name);
                })
                .startWith(redis).cast(String.class);
    }

}
