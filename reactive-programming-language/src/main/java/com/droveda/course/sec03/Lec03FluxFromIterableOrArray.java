package com.droveda.course.sec03;

import com.droveda.course.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {

    public static void main(String[] args) {

        var list = List.of("a", "b", "c");

        Flux.fromIterable(list)
                .subscribe(Util.subscriber());


        Integer[] arr = {1, 2, 3, 4, 5, 6};

        Flux.fromArray(arr)
                .subscribe(Util.subscriber());

    }

}