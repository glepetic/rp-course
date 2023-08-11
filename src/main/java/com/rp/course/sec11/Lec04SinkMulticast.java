package com.rp.course.sec11;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec04SinkMulticast {

    public static void main(String[] args) {
        buffer();
        Util.sleepSeconds(2);
        disableHistory();
    }

    private static void buffer() {

        Sinks.Many<Object> sink = Sinks.many()
                .multicast()
                .onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");

        // only one to get "hi" and "how are you" because this sink buffers and emits everything to the first subscriber
        flux.subscribe(Util.subscriber("Sub"));

        // these 3 receive only "?"
        flux.subscribe(Util.subscriber("SemiLateSub1"));
        flux.subscribe(Util.subscriber("SemiLateSub2"));
        flux.subscribe(Util.subscriber("SemiLateSubN"));

        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("LateSub")); // receives nothing

        sink.tryEmitComplete();

    }

    private static void disableHistory() {

        Sinks.Many<Object> sink = Sinks.many()
                .multicast()
                .directAllOrNothing(); // does not keep buffer for any subscribers

        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("message 1");
        sink.tryEmitNext("message 2");

        flux.subscribe(Util.subscriber("NoHistorySub1"));
        flux.subscribe(Util.subscriber("NoHistorySub2"));

        sink.tryEmitNext("message 3");

        // no complete signal

    }

}
