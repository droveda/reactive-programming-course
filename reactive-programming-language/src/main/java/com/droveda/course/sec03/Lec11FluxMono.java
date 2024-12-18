package com.droveda.course.sec03;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * To convert a Flux <---> Mono
 */
public class Lec11FluxMono {

    public static void main(String[] args) {

        monoToFlux();

        var flux = Flux.range(1, 10);
        flux.next()
                .subscribe(Util.subscriber());

        Mono.from(flux)
                .subscribe(Util.subscriber());

    }


    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty(); //null
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber());

    }

    private static void monoToFlux() {
        var mono = getUserName(1);
        save(Flux.from(mono));
    }

}
