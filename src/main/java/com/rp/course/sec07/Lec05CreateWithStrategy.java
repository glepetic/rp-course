package com.rp.course.sec07;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class Lec05CreateWithStrategy {

    public static void main(String[] args) {

        // 75% of bufferSize before it fills the buffer, for 16 -> 12
        System.setProperty("reactor.bufferSize.small", "16"); // 16 is the minimum value

        // items are published faster than they are processed
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 151 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed: " + i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                }, FluxSink.OverflowStrategy.DROP)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

}
