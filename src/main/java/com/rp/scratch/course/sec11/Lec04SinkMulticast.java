package com.rp.scratch.course.sec11;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec04SinkMulticast {

    public static void main(String[] args) {
        buffer();
        ScratchUtil.sleepSeconds(2);
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
        flux.subscribe(ScratchUtil.subscriber("Sub"));

        // these 3 receive only "?"
        flux.subscribe(ScratchUtil.subscriber("SemiLateSub1"));
        flux.subscribe(ScratchUtil.subscriber("SemiLateSub2"));
        flux.subscribe(ScratchUtil.subscriber("SemiLateSubN"));

        sink.tryEmitNext("?");

        flux.subscribe(ScratchUtil.subscriber("LateSub")); // receives nothing

        sink.tryEmitComplete();

    }

    private static void disableHistory() {

        Sinks.Many<Object> sink = Sinks.many()
                .multicast()
                .directAllOrNothing(); // does not keep buffer for any subscribers

        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("message 1");
        sink.tryEmitNext("message 2");

        flux.subscribe(ScratchUtil.subscriber("NoHistorySub1"));
        flux.subscribe(ScratchUtil.subscriber("NoHistorySub2"));

        sink.tryEmitNext("message 3");

        // no complete signal

    }

}
