package com.droveda.playground.sec05.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

//@Component
@Order(2)
public class WebFilterDemo2 implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(WebFilterDemo2.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("received 2");

        return chain.filter(exchange);
    }
}
