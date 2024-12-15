package com.droveda.course.sec02;

import com.droveda.course.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {

    public static void main(String[] args) {

        getUserName(1)
                .subscribe(Util.subscriber("my-sub"));

        getUserName(3)
                .subscribe(
                        s -> System.out.println(s)
                );

    }

    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty(); //null
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

}
