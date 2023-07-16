package com.rp.scratch.course.sec06;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03SusbcribeOnMultipleItems {

    public static void main(String[] args) {

        /*
            Each Subscriber gets its own thread. Each thread emits all items to each subscriber.
            (No multiple threads per publisher/subscriber)
         */

        Flux<Object> flux = Flux.create(fluxSink -> {
                    ScratchUtil.printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                        ScratchUtil.sleepSeconds(1);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> ScratchUtil.printThreadName("next " + i));

        flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> ScratchUtil.printThreadName("sub " + v));

        flux
                .subscribeOn(Schedulers.parallel())
                .subscribe(v -> ScratchUtil.printThreadName("sub " + v));

        ScratchUtil.sleepSeconds(5);

        Runnable runnable = () -> flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> ScratchUtil.printThreadName("sub " + v));

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }

        ScratchUtil.sleepSeconds(5);

    }

}
