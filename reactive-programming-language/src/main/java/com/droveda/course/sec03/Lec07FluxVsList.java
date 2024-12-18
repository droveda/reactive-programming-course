package com.droveda.course.sec03;

import com.droveda.course.common.Util;
import com.droveda.course.sec01.subscriber.SubscriberImpl;
import com.droveda.course.sec03.helper.NameGenerator;

public class Lec07FluxVsList {

    public static void main(String[] args) {

        // this approach it will wait for 10 seconds and then returns all the values
//        var list = NameGenerator.getNamesList(10);
//        System.out.println(list);

        //using reactive it will return one by one of the names, and this is very cool
        var subscriber = new SubscriberImpl();

        NameGenerator.getNamesFlux(10)
                .subscribe(subscriber);

        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();

        System.out.println("---");

        NameGenerator.getNamesFlux(10)
                .subscribe(Util.subscriber());


    }

}
