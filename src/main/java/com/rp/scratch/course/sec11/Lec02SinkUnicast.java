package com.rp.scratch.course.sec11;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec02SinkUnicast {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many()
                .unicast()
                .onBackpressureBuffer(); // can pass your own queue as parameter to use as buffer

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(ScratchUtil.subscriber("ValidSub"));
        flux.subscribe(ScratchUtil.subscriber("ErrorSub")); // error because sink is unicast

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");
        sink.tryEmitComplete();

    }

}
