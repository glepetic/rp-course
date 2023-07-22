package com.rp.scratch.course.sec07;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec03Latest {

    public static void main(String[] args) {

        // like drop, but keeps the latest item emitted "cached"
        // this is why it always gets the last item before completion, in this case 200

        // 75% of bufferSize before it fills the buffer, for 16 -> 12
        System.setProperty("reactor.bufferSize.small", "16"); // 16 is the minimum value

        // items are published faster than they are processed
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 201; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed: " + i);
                        ScratchUtil.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureLatest() // by default stops at 256 (Queues property: reactor.bufferSize.small)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> ScratchUtil.sleepMillis(10))
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(60);

    }

}
