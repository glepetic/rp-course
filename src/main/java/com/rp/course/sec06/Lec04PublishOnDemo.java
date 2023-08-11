package com.rp.course.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04PublishOnDemo {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
                    Util.printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> Util.printThreadName("next " + i));

        flux
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.printThreadName("next " + i))
                .publishOn(Schedulers.parallel())
                .subscribe(v -> Util.printThreadName("sub " + v));

        Util.sleepSeconds(5);

    }

}
