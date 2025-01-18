package com.droveda.playground.tests.sec08;

import com.droveda.playground.sec08.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.nio.file.Path;

/*
    just for demo
 */
public class ProductsUploadDownloadTest {

    private static final Logger log = LoggerFactory.getLogger(ProductsUploadDownloadTest.class);

    private final ProductClient productClient = new ProductClient();

    @Test
    void upload() {
        var flux = Flux.range(1, 1_000_000)
                .map(i -> new ProductDto(null, "product-" + i, i));
//                .delayElements(Duration.ofSeconds(2));

        productClient.uploadProducts(flux)
                .doOnNext(r -> log.info("received: {}", r))
                .then()
                .as(StepVerifier::create)
                .expectComplete()
                .verify();

    }

    @Test
    void download() {
        productClient.downloadProducts()
                .map(ProductDto::toString)
                .as(flux -> FileWriter.create(flux, Path.of("products.txt")))
                .as(StepVerifier::create)
                .expectComplete()
                .verify();

    }

}
