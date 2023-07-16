package com.rp.scratch.course.sec01;

import com.rp.scratch.util.ScratchUtil;
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
        mono.subscribe(ScratchUtil.onNextPrintValue());

        // Provide onNext and onError consumers -> prints message
        mono.subscribe(
                ScratchUtil.onNextPrintValue(),
                ScratchUtil.onErrorPrintMessage()
        );

        // Provide onNext, onError and onComplete consumers -> prints message
        mono.subscribe(
                ScratchUtil.onNextPrintValue(),
                ScratchUtil.onErrorPrintMessage(),
                ScratchUtil.onCompletePrintCompleted()
        );


    }

}
