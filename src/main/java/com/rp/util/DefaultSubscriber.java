package com.rp.util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber<T> implements Subscriber<T> {

    private final String name;

    public DefaultSubscriber() {
        this.name = "";
    }

    public DefaultSubscriber(String name) {
        this.name = name + " ";
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        System.out.println(name + "Received: " + t);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + "Error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + "Completed");
    }

}
