package com.rp.scratch.course.sec01;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    public static void main(String[] args) {

        // publisher
        Mono<String> mono = Mono.just("ball");

        // Provide no consumers
        mono.subscribe();

        // Provide onNext consumer
        mono.subscribe(ScratchUtil.onNextPrintValue());

        // Provide onNext, onError consumers
        mono.subscribe(
                ScratchUtil.onNextPrintValue(),
                ScratchUtil.onErrorPrintMessage()
        );

        // Provide onNext, onError, onComplete consumers
        mono.subscribe(
                ScratchUtil.onNextPrintValue(),
                ScratchUtil.onErrorPrintMessage(),
                ScratchUtil.onCompletePrintCompleted()
        );

    }

}
