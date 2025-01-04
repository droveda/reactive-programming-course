package com.droveda.course;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec08ContextTest {

    Mono<String> getWelcomeMessage() {

        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome to %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });

    }

    @Test
    public void welcomeMessageTest() {

        var options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));

        StepVerifier.create(getWelcomeMessage(), options)
                .expectNext("Welcome to sam")
                .verifyComplete();

    }

    @Test
    public void welcomeMessageTestError() {

        var options = StepVerifierOptions.create().withInitialContext(Context.empty());

        StepVerifier.create(getWelcomeMessage(), options)
                .expectErrorMessage("unauthenticated")
                .verify();

    }

}
