package com.rp.scratch.course.sec02;

import com.rp.scratch.util.ScratchUtil;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec06Subscription {

    public static void main(String[] args) {

        AtomicReference<Subscription> reference = new AtomicReference<>();

       Flux.range(1, 20)
               .log()
               .subscribeWith(new Subscriber<Integer>() {
                   @Override
                   public void onSubscribe(Subscription subscription) {
                       System.out.println("Received Sub: " + subscription);
                       reference.set(subscription);
                   }

                   @Override
                   public void onNext(Integer integer) {
                       System.out.println("onNext: " + integer);
                   }

                   @Override
                   public void onError(Throwable throwable) {
                       System.out.println("onError: " + throwable.getMessage());
                   }

                   @Override
                   public void onComplete() {
                       System.out.println("onComplete");
                   }

               });

       ScratchUtil.sleepSeconds(3);
       reference.get().request(3);
       ScratchUtil.sleepSeconds(5);
        reference.get().request(3);
        ScratchUtil.sleepSeconds(5);
        System.out.println("going to cancel");
        reference.get().cancel();
        ScratchUtil.sleepSeconds(3);
        // this does nothing because you cannot request anything after you cancel, it simply has no effect
        reference.get().request(4);
        ScratchUtil.sleepSeconds(3);

    }

}
