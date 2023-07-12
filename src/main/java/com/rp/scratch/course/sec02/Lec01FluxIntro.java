package com.rp.scratch.course.sec02;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec01FluxIntro {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux.subscribe(
                ScratchUtil.onNextPrintValue(),
                ScratchUtil.onErrorPrintMessage(),
                ScratchUtil.onCompletePrintCompleted()
        );

        Flux<Integer> emptyFlux = Flux.empty();

        emptyFlux.subscribe(
                ScratchUtil.onNextPrintValue(),
                ScratchUtil.onErrorPrintMessage(),
                ScratchUtil.onCompletePrintCompleted()
        );

        Flux<Object> genericFlux = Flux.just(1, 2, 3, "a", ScratchUtil.faker().name().fullName());

        genericFlux.subscribe(
                ScratchUtil.onNextPrintValue(),
                ScratchUtil.onErrorPrintMessage(),
                ScratchUtil.onCompletePrintCompleted()
        );

    }

}
