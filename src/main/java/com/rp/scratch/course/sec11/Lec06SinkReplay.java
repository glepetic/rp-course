package com.rp.scratch.course.sec11;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec06SinkReplay {

    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many()
                .replay() // works like cache
                .all();

        Flux<Object> flux = sink.asFlux();

        flux.subscribe(ScratchUtil.subscriber("Sub1"));

        sink.tryEmitNext("everyone");

        flux.subscribe(ScratchUtil.subscriber("Sub2"));

        sink.tryEmitNext("gets");

        flux.subscribe(ScratchUtil.subscriber("Sub3"));

        sink.tryEmitNext("everything");
        sink.tryEmitNext("!");

        sink.tryEmitComplete();

    }

}
