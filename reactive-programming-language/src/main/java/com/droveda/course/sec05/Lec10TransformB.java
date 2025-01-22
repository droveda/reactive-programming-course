package com.droveda.course.sec05;

import com.droveda.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec10TransformB {

    private static final Logger log = LoggerFactory.getLogger(Lec10TransformB.class);

    record Customer(int id, String name) {

        public String getUserUpperCase() {
            return name.toUpperCase();
        }

    }

    record PurchaseOrder(String productName, int price, int quantity) {
    }

    public static void main(String[] args) {

//        var idDebugEnabled = false;
//
//        getCustomers()
//                .transform(idDebugEnabled ? addDebugger() : Function.identity())
//                .subscribe(Util.subscriber());
//
//
//        getPurchasedOrders()
//                .transform(addDebugger())
//                .subscribe(Util.subscriber());
//
//        System.out.println(getTeste().apply("droveda"));
//
//        new Lec10TransformB().teste();
//
//        System.out.println(getTeste2().apply("droveda"));

        getCustomers()
                .transform(getTest2())
                .subscribe(Util.subscriber());
    }

    private static Function<Flux<Customer>, Flux<String>> getTest2() {
        return flux -> flux.map(c -> c.getUserUpperCase() + " |--");
    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchasedOrders() {
        return Flux.range(1, 5)
                .map(i -> new PurchaseOrder(Util.faker().commerce().productName(), i, i * 10));
    }


}
