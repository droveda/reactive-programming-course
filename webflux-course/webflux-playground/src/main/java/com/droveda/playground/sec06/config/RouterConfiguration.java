package com.droveda.playground.sec06.config;

import com.droveda.playground.sec06.exceptions.CustomerNotFoundException;
import com.droveda.playground.sec06.exceptions.InvalidInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfiguration {

    private final CustomerRequestHandler handler;

    private final ApplicationExceptionHandler exceptionHandler;

    public RouterConfiguration(CustomerRequestHandler handler, ApplicationExceptionHandler exceptionHandler) {
        this.handler = handler;
        this.exceptionHandler = exceptionHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> customerRoutes() {
        return RouterFunctions.route()
                .GET(request -> false, new HandlerFunction<ServerResponse>() {
                    @Override
                    public Mono<ServerResponse> handle(ServerRequest request) {
                        return null;
                    }
                })
                .path("customers", this::customerRoutes2)
//                .GET("customers", handler::allCustomers)
//                .GET("customers/paginated", handler::paginatedCustomer)
//                .GET("customers/{id}", handler::getCustomer)
                .POST("customers", handler::saveCustomer)
                .PUT("customers/{id}", handler::updateCustomer)
                .DELETE("customers/{id}", handler::deleteCustomer)
                .onError(CustomerNotFoundException.class, (ex, req) -> exceptionHandler.handleException(ex, req))
                .onError(InvalidInputException.class, exceptionHandler::handleException)
//                .filter((req, next) -> {
//                     return ServerResponse.badRequest().build();
//                })
//                .filter((req, next) -> {
//                    return ServerResponse.badRequest().build();
//                })
                .build();
    }

    private RouterFunction<ServerResponse> customerRoutes2() {
        return RouterFunctions.route()
                .GET("/paginated", handler::paginatedCustomer)
                .GET("/{id}", handler::getCustomer)
                .GET(handler::allCustomers)
                .build();
    }


}
