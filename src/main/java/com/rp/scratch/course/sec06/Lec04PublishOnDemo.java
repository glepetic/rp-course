package com.rp.scratch.course.sec06;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04PublishOnDemo {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
                    ScratchUtil.printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> ScratchUtil.printThreadName("next " + i));

        flux
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> ScratchUtil.printThreadName("next " + i))
                .publishOn(Schedulers.parallel())
                .subscribe(v -> ScratchUtil.printThreadName("sub " + v));

        ScratchUtil.sleepSeconds(5);

    }

}
