package com.droveda.course.sec13;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/*
    Context is for providing metadata about the request (similar to HTTP headers)
 */
public class Lec01Context {

    private static final Logger log = LoggerFactory.getLogger(Lec01Context.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("user", "Sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {

            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s!".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

}
