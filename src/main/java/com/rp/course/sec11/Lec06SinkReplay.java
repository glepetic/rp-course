package com.rp.course.sec11;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec06SinkReplay {

    public static void main(String[] args) {

        Sinks.Many<Object> sink = Sinks.many()
                .replay() // works like cache
                .all();

        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Sub1"));

        sink.tryEmitNext("everyone");

        flux.subscribe(Util.subscriber("Sub2"));

        sink.tryEmitNext("gets");

        flux.subscribe(Util.subscriber("Sub3"));

        sink.tryEmitNext("everything");
        sink.tryEmitNext("!");

        sink.tryEmitComplete();

    }

}
