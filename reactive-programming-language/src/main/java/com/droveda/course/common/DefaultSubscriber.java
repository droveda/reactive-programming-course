package com.droveda.course.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSubscriber.class);

    private final String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        LOGGER.info("{} received: {}", this.name, item);
    }

    @Override
    public void onError(Throwable t) {
        LOGGER.error("{} error", this.name, t);
    }

    @Override
    public void onComplete() {
        LOGGER.info("{} received completed!", this.name);
    }

}
