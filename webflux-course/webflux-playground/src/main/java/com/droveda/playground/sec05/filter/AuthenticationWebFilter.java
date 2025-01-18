package com.droveda.playground.sec05.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Component
@Order(1)
public class AuthenticationWebFilter implements WebFilter {

    @Autowired
    private FilterErrorHandler filterErrorHandler;

    private static final Map<String, Category> TOKEN_CATEGORY = Map.of(
            "secret123", Category.STANDARD,
            "secret456", Category.PRIME
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        var token = exchange.getRequest().getHeaders().getFirst("auth-token");

        if (Objects.nonNull(token) && TOKEN_CATEGORY.containsKey(token)) {
            exchange.getAttributes().put("category", TOKEN_CATEGORY.get(token));

            return chain.filter(exchange);
        }

//        return Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED));

        return filterErrorHandler.senProblemDetail(exchange, HttpStatus.UNAUTHORIZED, "Set the valid token");
    }
}
