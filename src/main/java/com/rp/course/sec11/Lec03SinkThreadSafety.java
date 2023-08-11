package com.rp.course.sec11;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lec03SinkThreadSafety {

    public static void main(String[] args) {

        // reactor documentation -> by default, Sinks are threadsafe

        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Sinks.Many<Object> sink2 = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();
        Flux<Object> flux2 = sink2.asFlux();

        List<Object> list = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();

        flux.subscribe(list::add);
        flux2.subscribe(list2::add);

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                // since the emit result is not being handled, the result will be inconsistent
                sink.tryEmitNext(j);
            });
        }

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                // if there is a problem emitting, it will automatically retry which guarantees the list2 to have the correct size
                sink2.emitNext(j, (s,e) -> true);
            });
        }

        Util.sleepSeconds(3);
        System.out.println("List size will be different each time: " + list.size());
        System.out.println("List size will be always the same: " + list2.size());

    }

}
