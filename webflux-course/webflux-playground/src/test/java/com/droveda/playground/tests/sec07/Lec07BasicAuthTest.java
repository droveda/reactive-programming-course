package com.droveda.playground.tests.sec07;

import com.droveda.playground.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class Lec07BasicAuthTest extends AbstractWebClient {

    private final WebClient client = createWebClient(b -> {
        b.defaultHeaders(h -> h.setBasicAuth("java", "secret"));
    });

    @Test
    void basicAuth() {

        client.get()
                .uri("/lec07/product/{id}", 1)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(print())
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();

    }

}
