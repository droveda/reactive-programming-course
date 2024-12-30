package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lec10Transform {

    private static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    record Customer(int id, String name) {
    }

    record PurchaseOrder(String productName, int price, int quantity) {
    }

    public static void main(String[] args) {

        var idDebugEnabled = false;

        getCustomers()
                .transform(idDebugEnabled ? addDebugger() : Function.identity())
                .subscribe(Util.subscriber());


        getPurchasedOrders()
                .transform(addDebugger())
                .subscribe(Util.subscriber());

        System.out.println(getTeste().apply("droveda"));

        new Lec10Transform().teste();

        System.out.println(getTeste2().apply("droveda"));

    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchasedOrders() {
        return Flux.range(1, 5)
                .map(i -> new PurchaseOrder(Util.faker().commerce().productName(), i, i * 10));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error: {}", err.getMessage()));
    }

    private static <T> UnaryOperator<String> getTeste() {
        return s -> s + " from MyClass";
    }

    private static <T, R> Function<String, Integer> getTeste2() {
        return s -> s.length();
    }

    private String teste() {
        var a = new MyClass2().apply("hello!");
        System.out.println(a);
        return new MyClass().apply("test");
    }

    public class MyClass implements UnaryOperator<String> {

        @Override
        public String apply(String s) {
            return s + " from MyClass";
        }
    }

    public class MyClass2 implements Function<String, Integer> {

        @Override
        public Integer apply(String s) {
            System.out.println("s = " + s);
            return s.length();
        }
    }

}
