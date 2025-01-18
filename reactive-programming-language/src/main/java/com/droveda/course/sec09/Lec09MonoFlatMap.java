package com.droveda.course.sec09;

import com.droveda.course.common.Util;
import com.droveda.course.sec09.applications.PaymentService;
import com.droveda.course.sec09.applications.UserService;

/*
  Sequential non-blocking IO calls!
  flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
 */
public class Lec09MonoFlatMap {

    public static void main(String[] args) {

        /*
        we have username,
        we have to get the user id from the username
        then we have to get the balance from the user id
         */

//        UserService.getUserId("sam")
//                .map(userId -> "Hello " + userId)
//                        .subscribe(Util.subscriber());

        UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());

        UserService.getUserId("mike")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());

    }

}
