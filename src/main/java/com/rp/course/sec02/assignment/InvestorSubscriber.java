package com.rp.course.sec02.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

public class InvestorSubscriber implements Subscriber<Integer> {

    private Subscription subscription;
    private final CountDownLatch latch;

    private InvestorSubscriber(CountDownLatch latch) {
        this.latch = latch;
    }

    public static InvestorSubscriber fromLatch(CountDownLatch latch) {
        return new InvestorSubscriber(latch);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        System.out.println("Received Sub: " + this.subscription);
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer price) {
        System.out.println(LocalDateTime.now() + ": Price: " + price);
        if (price < 90 || price > 110) {
            this.subscription.cancel();
            latch.countDown();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError: " + throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
        latch.countDown();
    }

}
