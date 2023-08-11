package com.rp.course.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class Lec04FluxFromStream {

    public static void main(String[] args) {

        Stream<String> myStream = Stream.of("hello", "world", "!");
        Flux<String> flux = Flux.fromStream(myStream);

        // Uses and closes stream (like Java 8 Stream API
        flux.subscribe(Util.onNextPrintValue(), Util.onErrorPrintMessage(), Util.onCompletePrintCompleted());

        // Stream is closed so it throws error
        flux.subscribe(Util.onNextPrintValue(), Util.onErrorPrintMessage(), Util.onCompletePrintCompleted());

        // Issue mentioned above gets resolved with a Stream supplier since it generates a new Stream for every subscriber
        Flux<String> safeFlux = Flux.fromStream(() -> Stream.of("bye", "universe", "?"));

        safeFlux.subscribe(Util.onNextPrintValue(), Util.onErrorPrintMessage(), Util.onCompletePrintCompleted());

        safeFlux.subscribe(Util.onNextPrintValue(), Util.onErrorPrintMessage(), Util.onCompletePrintCompleted());


    }

}
