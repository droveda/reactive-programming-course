package com.droveda.course.sec01.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        LOGGER.info("received: {}", email);
    }

    @Override
    public void onError(Throwable t) {
        LOGGER.error("error", t);
    }

    @Override
    public void onComplete() {
        LOGGER.info("completed!");
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
