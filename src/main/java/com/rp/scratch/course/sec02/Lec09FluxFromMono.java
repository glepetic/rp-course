package com.rp.scratch.course.sec02;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec09FluxFromMono {

    public static void main(String[] args) {

        Mono<String> mono = Mono.just("a");

        // Mono -> Flux
        Flux<String> flux1 = mono.flux();
        // from any Publisher
        Flux<String> flux2 = Flux.from(mono);

        flux1.subscribe(ScratchUtil.onNextPrintValue());
        flux2.subscribe(ScratchUtil.onNextPrintValue());

    }

    private static void doSomething(Flux<String> flux) {}

}
