package com.rp.course.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    public static void main(String[] args) {

        // publisher
        Mono<String> mono = Mono.just("ball");

        // Provide no consumers
        mono.subscribe();

        // Provide onNext consumer
        mono.subscribe(Util.onNextPrintValue());

        // Provide onNext, onError consumers
        mono.subscribe(
                Util.onNextPrintValue(),
                Util.onErrorPrintMessage()
        );

        // Provide onNext, onError, onComplete consumers
        mono.subscribe(
                Util.onNextPrintValue(),
                Util.onErrorPrintMessage(),
                Util.onCompletePrintCompleted()
        );

    }

}
