package com.droveda.playground.sec06.config;

import com.droveda.playground.sec06.dto.CustomerDto;
import com.droveda.playground.sec06.exceptions.ApplicationExceptions;
import com.droveda.playground.sec06.service.CustomerService;
import com.droveda.playground.sec06.validator.RequestValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomerRequestHandler {

    private final CustomerService service;

    public CustomerRequestHandler(CustomerService service) {
        this.service = service;
    }

    public Mono<ServerResponse> allCustomers(ServerRequest request) {
//        request.pathVariable()
//        request.headers();
//        request.queryParam();

        return this.service.getAllCustomers()
                .as(flux -> ServerResponse.ok().body(flux, CustomerDto.class));
    }

    public Mono<ServerResponse> paginatedCustomer(ServerRequest request) {
        var page = request.queryParam("page").map(Integer::parseInt).orElse(1);
        var size = request.queryParam("size").map(Integer::parseInt).orElse(3);

        return this.service.getAllCustomers(page, size)
                .collectList()
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> getCustomer(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariable("id"));

        return this.service.getCustomerById(id)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        return request.bodyToMono(CustomerDto.class)
                .transform(RequestValidator.validate())
                .as(this.service::saveCustomer)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> updateCustomer(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariable("id"));

        return request.bodyToMono(CustomerDto.class)
                .transform(RequestValidator.validate())
                .as(validatedReq -> this.service.updateCustomer(id, validatedReq))
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariable("id"));

        return this.service.deleteCustomerById(id)
                .filter(b -> b)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(id))
                .then(ServerResponse.ok().build());
    }

}
