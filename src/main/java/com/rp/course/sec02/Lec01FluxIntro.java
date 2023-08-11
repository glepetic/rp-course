package com.rp.course.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxIntro {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux.subscribe(
                Util.onNextPrintValue(),
                Util.onErrorPrintMessage(),
                Util.onCompletePrintCompleted()
        );

        Flux<Integer> emptyFlux = Flux.empty();

        emptyFlux.subscribe(
                Util.onNextPrintValue(),
                Util.onErrorPrintMessage(),
                Util.onCompletePrintCompleted()
        );

        Flux<Object> genericFlux = Flux.just(1, 2, 3, "a", Util.faker().name().fullName());

        genericFlux.subscribe(
                Util.onNextPrintValue(),
                Util.onErrorPrintMessage(),
                Util.onCompletePrintCompleted()
        );

    }

}
