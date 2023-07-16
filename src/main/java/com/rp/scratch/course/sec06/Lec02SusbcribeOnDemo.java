package com.rp.scratch.course.sec06;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SusbcribeOnDemo {

    public static void main(String[] args) {

        /*
        Pipeline is read upwards. Starting from subscribe() and so on.
        This means that whatever is between subscribe() and subscribeOn() will be executed in the current thread.

        parallel -> 1 thread per CPU -> used for high CPU consuming tasks
        boundedElastic -> 10 threads per CPU -> used for network or high time consuming tasks
         */

        Flux<Object> flux = Flux.create(fluxSink -> {
                    ScratchUtil.printThreadName("create");
                    fluxSink.next(1);
                })
                // closest scheduler to the publisher is the one used for emitted values
                .subscribeOn(Schedulers.newParallel("upwards"))
                .doOnNext(i -> ScratchUtil.printThreadName("next " + i));

        flux
                .doFirst(() -> ScratchUtil.printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> ScratchUtil.printThreadName("first1"))
                .subscribe(v -> ScratchUtil.printThreadName("sub " + v));

        Runnable runnable = () -> flux
                .doFirst(() -> ScratchUtil.printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> ScratchUtil.printThreadName("first1"))
                .subscribe(v -> ScratchUtil.printThreadName("sub " + v));

        for (int i = 0; i < 1; i++) {
            new Thread(runnable).start();
        }

        ScratchUtil.sleepSeconds(5);

    }

}
