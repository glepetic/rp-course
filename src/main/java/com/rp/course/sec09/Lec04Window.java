package com.rp.course.sec09;

import com.rp.course.sec09.helper.EventStreamer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec04Window {

    private final static EventStreamer eventStreamer = EventStreamer.instance();
    private final static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {

        /*
            Just like buffer, it can divide the items given a maxSize, a duration or both. It can also overlap/drop.
            The difference is that window returns a Flux<Flux<T>> whereas buffer returns a Flux<List<T>>
         */

        eventStreamer
                .eventStream(300)
                .window(5)
                .flatMap(Lec04Window::saveEvents)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }

    private static Mono<Integer> saveEvents(Flux<String> flux) {
        return flux
                .doOnNext(evt -> System.out.println("saving " + evt))
                .doOnComplete(() -> System.out.println("saved this batch"))
                .doOnComplete(() -> System.out.println("------------"))
                .then(Mono.just(counter.getAndIncrement()));
    }

}
