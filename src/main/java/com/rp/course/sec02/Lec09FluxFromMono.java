package com.rp.course.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09FluxFromMono {

    public static void main(String[] args) {

        Mono<String> mono = Mono.just("a");

        // Mono -> Flux
        Flux<String> flux1 = mono.flux();
        // from any Publisher
        Flux<String> flux2 = Flux.from(mono);

        flux1.subscribe(Util.onNextPrintValue());
        flux2.subscribe(Util.onNextPrintValue());

    }

    private static void doSomething(Flux<String> flux) {}

}
