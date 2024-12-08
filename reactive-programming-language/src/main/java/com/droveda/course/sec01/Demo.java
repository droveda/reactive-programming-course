package com.droveda.course.sec01;

import com.droveda.course.sec01.publisher.PublisherImpl;
import com.droveda.course.sec01.subscriber.SubscriberImpl;

import java.time.Duration;

/**
 * 1. publisher does not produce data unless subscriber requests for it.
 * 2. publisher will produce only <= subscriber requested items. publisher can also produce 0 items!
 * 3. subscriber can cancel the subscription. producer should stop at that moment as subscriber is no longer interested in consuming data
 * 4. producer can send the error signal
 */

public class Demo {

    public static void main(String[] args) throws InterruptedException {
//        demo1();
//        demo2();
//        demo3();
        demo4();
    }

    private static void demo1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(3);
    }

    private static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().cancel();

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(3);
    }

    private static void demo4() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());

    }

}
