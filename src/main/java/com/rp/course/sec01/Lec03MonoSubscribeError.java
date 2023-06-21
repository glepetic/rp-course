package com.rp.course.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribeError {

    public static void main(String[] args) {

        // publisher
        Mono<Integer> mono = Mono.just("ball")
                .map(String::length)
                .map(length -> length / 0);

        // Provide no consumers -> throws exception
        mono.subscribe();

        // Provide onNext consumer -> throws exception
        mono.subscribe(Util.onNextPrintValue());

        // Provide onNext and onError consumers -> prints message
        mono.subscribe(
                Util.onNextPrintValue(),
                Util.onErrorPrintMessage()
        );

        // Provide onNext, onError and onComplete consumers -> prints message
        mono.subscribe(
                Util.onNextPrintValue(),
                Util.onErrorPrintMessage(),
                Util.onCompletePrintCompleted()
        );


    }

}
