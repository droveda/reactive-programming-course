package com.droveda.playground.sec09.service;

import com.droveda.playground.sec09.dto.ProductDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataSetupService implements CommandLineRunner {

    private final ProductService service;

    public DataSetupService(ProductService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> new ProductDto(null, "product-" + i, ThreadLocalRandom.current().nextInt(1, 100)))
                .flatMap(dto -> service.saveProduct(Mono.just(dto)))
                .subscribe();
    }
}
