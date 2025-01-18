package com.droveda.playground.tests.sec05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest(properties = "sec=sec05")
public class CustomerServiceTest {

    @Autowired
    private WebTestClient client;

    //just validate HTTP response status codes!
    //unauthorized - no token
    //unauthorized - invalid token
    //standard category - GET - success
    //standard category - POST/PUT/DELETE - forbidden
    //prime category - GET - success
    //prime category - POST/PUT/DELETE - success

    @Test
    void noToken() {
        client.get()
                .uri("/customers")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void invalidToken() {
        client.get()
                .uri("/customers")
                .header("auth-token", "secret1234")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void standardCategorySuccess() {
        client.get()
                .uri("/customers")
                .header("auth-token", "secret123")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);
    }

    @Test
    void standardCategoryForbidden() {
        client.delete()
                .uri("/customers/11")
                .header("auth-token", "secret123")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void primeCategorySuccess() {
        client.get()
                .uri("/customers")
                .header("auth-token", "secret456")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);
    }

    @Test
    void primeCategoryDeleteSuccess() {
        client.delete()
                .uri("/customers/10")
                .header("auth-token", "secret456")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);
    }

}
