package com.droveda.course.sec13;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Map;

/*
    Context is an immutable map. We can append additional info!
 */
public class Lec02ContextAppendUpdate {

    private static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx -> Context.of("user", "Mike"))
                //.contextWrite(ctx -> Context.empty())
                .contextWrite(Context.of("a", "b").putAllMap(Map.of("c", "d")))
                .contextWrite(Context.of("user", "Sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            log.info("{}", ctx);

            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s!".formatted(ctx.<String>get("user")));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

}
