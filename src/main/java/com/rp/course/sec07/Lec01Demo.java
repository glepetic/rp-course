package com.rp.course.sec07;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec01Demo {

    public static void main(String[] args) {

        // by default Reactor uses buffer for backpressure (keeps items in memory)

        // items are published faster than they are processed
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 501; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed: " + i);
                    }
                    fluxSink.complete();
                })
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

}
