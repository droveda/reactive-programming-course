package com.droveda.course.sec09;

import com.droveda.course.common.Util;
import com.droveda.course.sec09.applications.OrderService;
import com.droveda.course.sec09.applications.User;
import com.droveda.course.sec09.applications.UserService;

public class Lec11FluxFlatMap {

    public static void main(String[] args) {
        /*
        get all the orders from order service!
         */

        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

}
