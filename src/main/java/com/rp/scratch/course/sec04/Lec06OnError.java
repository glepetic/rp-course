package com.rp.scratch.course.sec04;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06OnError {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
                // Gives a fallback value for error then subscription gets canceled -> EAGER
                .onErrorReturn(-1)
                .subscribe(ScratchUtil.subscriber("ReturnSubscriber"));

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
                // Gives a fallback function to produce a fallback value then subscription gets canceled -> LAZY
                .onErrorResume(e -> fallback())
                .subscribe(ScratchUtil.subscriber("ResumeSubscriber"));

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
                // Gives a fallback function to produce a fallback value then subscription gets canceled -> LAZY
                .onErrorContinue((e, errorValue) -> System.out.println("Error " + e.getMessage() + " ocurred while processing value: " + errorValue))
                .subscribe(ScratchUtil.subscriber("ContinueSubscriber"));

    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> ScratchUtil.faker().random().nextInt(100, 200));
    }

}
