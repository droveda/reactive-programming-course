package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {

    public static void main(String[] args) {

        /*
        Handle behaves like filter + map
        1 => -2
        4 => do not send
        7 => error
        everything else => send as it is
         */

        Flux.range(1, 10)
                .filter(i -> i != 7)
                .handle((item, sink) -> {
                    switch (item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> sink.error(new RuntimeException("Error occurred for: " + item));
                        default -> sink.next(item);
                    }
                })
                .subscribe(Util.subscriber());


    }

}
