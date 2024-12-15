package com.droveda.course.sec02;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {

    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {

        var list = List.of(1, 2, 3);

        Mono.fromCallable(() -> sum(list)) // using fromCallable it will delay the execution, only if a consumer is supplied
                .subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);

        return list.stream().mapToInt(a -> a).sum();
    }

}
