package com.droveda.course;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {

    private Flux<Integer> getItems() {
        return Flux.just(1, 2, 3)
                .log();
    }

    @Test
    void testFlux1() {
        StepVerifier.create(getItems(), 1)
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    void testFlux2() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

    @Test
    void testFlux3() {
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }

}
