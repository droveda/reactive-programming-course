package com.droveda.course.sec09.assignment;

import com.droveda.course.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<Product> getProduct(int id) {
        return Mono.zip(getProductName(id),
                        getReview(id),
                        getPrice(id)
                )
                .map(t -> new Product(t.getT1(), t.getT2(), t.getT3()));
    }


    private Mono<String> getProductName(int productId) {
        return get("/demo05/product/" + productId);

    }

    private Mono<String> getReview(int productId) {
        return get("/demo05/review/" + productId);
    }

    private Mono<String> getPrice(int productId) {
        return get("/demo05/price/" + productId);
    }

    private Mono<String> get(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();

    }

}
