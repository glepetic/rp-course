package com.rp.scratch.course.sec06;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05PubSubOn {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(sink -> {
                    for (int i = 0; i < 4; i++) {
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(i -> ScratchUtil.printThreadName("next " + i));

        flux
                .publishOn(Schedulers.parallel())
                .doOnNext(i -> ScratchUtil.printThreadName("next " + i))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> ScratchUtil.printThreadName("sub " + v));

        ScratchUtil.sleepSeconds(5);

    }

}
