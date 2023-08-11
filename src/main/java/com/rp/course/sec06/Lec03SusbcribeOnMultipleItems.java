package com.rp.course.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03SusbcribeOnMultipleItems {

    public static void main(String[] args) {

        /*
            Each Subscriber gets its own thread. Each thread emits all items to each subscriber.
            (No multiple threads per publisher/subscriber)
         */

        Flux<Object> flux = Flux.create(fluxSink -> {
                    Util.printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                        Util.sleepSeconds(1);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> Util.printThreadName("next " + i));

        flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> Util.printThreadName("sub " + v));

        flux
                .subscribeOn(Schedulers.parallel())
                .subscribe(v -> Util.printThreadName("sub " + v));

        Util.sleepSeconds(5);

        Runnable runnable = () -> flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> Util.printThreadName("sub " + v));

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(5);

    }

}
