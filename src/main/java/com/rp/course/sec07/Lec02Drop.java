package com.rp.course.sec07;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec02Drop {

    public static void main(String[] args) {

        // 75% of bufferSize before it fills the buffer, for 16 -> 12
        System.setProperty("reactor.bufferSize.small", "16"); // 16 is the minimum value

        List<Object> list = new ArrayList<>();

        // items are published faster than they are processed
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 501; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed: " + i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureDrop(list::add) // by default stops at 256 (Queues property: reactor.bufferSize.small)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
        System.out.println("List size: " + list.size());

    }

}
