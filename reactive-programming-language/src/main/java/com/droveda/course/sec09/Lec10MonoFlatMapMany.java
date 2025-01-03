package com.droveda.course.sec09;

import com.droveda.course.common.Util;
import com.droveda.course.sec09.applications.Order;
import com.droveda.course.sec09.applications.OrderService;
import com.droveda.course.sec09.applications.UserService;
import reactor.core.publisher.Flux;

public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {
        /*
        we have username
        get all user orders!
         */

        Flux<Order> flux = UserService.getUserId("mike")
                .flatMapMany(OrderService::getOrders);

        flux.subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

}
