package com.rp.course.sec11;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec05MultiDirectAll {

    public static void main(String[] args) {

        System.getProperty("reactor.bufferSize.small", "16");

        directAll();
        Util.sleepSeconds(10);
        directBestEffort();
        Util.sleepSeconds(10);

    }

    private static void directAll() {
        Sinks.Many<Object> sink = Sinks.many()
                .multicast()
                .directAllOrNothing(); // everyone gets the items, or no one does

        run(sink);
    }

    private static void directBestEffort() {
        Sinks.Many<Object> sink = Sinks.many()
                .multicast()
                .directBestEffort(); // whichever subscriber can get the items will get the items

        run(sink);
    }

    private static void run(Sinks.Many<Object> sink) {

        Flux<Object> flux = sink.asFlux();

        flux.subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("FastSub"));
        flux.delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber("SlowSub"));

        for (int i = 0; i < 1000; i++) {
            sink.tryEmitNext(i);
        }

        sink.tryEmitComplete();

    }

}
