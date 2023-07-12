package com.rp.scratch.course.sec07;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04Error {

    public static void main(String[] args) {

        // 75% of bufferSize before it fills the buffer, for 16 -> 12
        System.setProperty("reactor.bufferSize.small", "16");

        // items are published faster than they are processed
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed: " + i);
                        ScratchUtil.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureError() // by default stops at 256 (Queues property: reactor.bufferSize.small)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> ScratchUtil.sleepMillis(10))
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(60);

    }

}
