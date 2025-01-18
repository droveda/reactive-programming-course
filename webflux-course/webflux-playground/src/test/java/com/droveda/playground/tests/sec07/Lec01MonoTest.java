package com.droveda.playground.tests.sec07;

import com.droveda.playground.tests.sec07.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

public class Lec01MonoTest extends AbstractWebClient {

    private final WebClient client = createWebClient();

    @Test
    void simpleGet() throws InterruptedException {
        client.get()
                .uri("/lec01/product/1")
                .retrieve()
                .bodyToMono(Product.class)
                .doOnNext(print())
                .subscribe();

        Thread.sleep(Duration.ofSeconds(2).toMillis());
    }

    @Test
    void concurrentRequests() throws InterruptedException {

        for (int i = 1; i <= 100; i++) {

            client.get()
                    .uri("/lec01/product/{id}", i)
                    .retrieve()
                    .bodyToMono(Product.class)
                    .doOnNext(print())
                    .subscribe();

        }


        Thread.sleep(Duration.ofSeconds(2).toMillis());
    }

}
