package com.rp.course.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec01ThreadDemo {

    public static void main(String[] args) {

        // default behavior, everything executed in current thread (main thread in this case)
        Flux<Object> flux = Flux.create(fluxSink -> {
                    Util.printThreadName("create");
                    fluxSink.next(1);
                })
                .doOnNext(i -> Util.printThreadName("next " + i));

        flux.subscribe(v -> Util.printThreadName("sub " + v));

        // with a runnable manually ran on a chosen/created thread, the execution is done on the selected thread
        Runnable runnable = () -> flux.subscribe(v -> Util.printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        // equivalent of the above
        flux.subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> Util.printThreadName("sub " + v));

        Util.sleepSeconds(5);



    }



}
