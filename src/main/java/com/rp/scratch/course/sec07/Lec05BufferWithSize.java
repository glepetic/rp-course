package com.rp.scratch.course.sec07;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05BufferWithSize {

    public static void main(String[] args) {

        // 75% of bufferSize before it fills the buffer, for 16 -> 12
        System.setProperty("reactor.bufferSize.small", "16"); // 16 is the minimum value

        // items are published faster than they are processed
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 151 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed: " + i);
                        ScratchUtil.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                // by default tries to hold everything in memory, the maxSize parameter limits it to what value you configure
                // throws error when buffer is exceeded
                .onBackpressureBuffer(20, o -> System.out.println("Dropped: " + o))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> ScratchUtil.sleepMillis(10))
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(60);

    }

}
