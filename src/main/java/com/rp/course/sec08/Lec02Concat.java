package com.rp.course.sec08;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec02Concat {

    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d", "e");
        Flux<String> flux3 = Flux.just("f", "g");
        Flux<String> fluxError = Flux.error(() -> new RuntimeException("oops"));

        // Emits left then right. Right is emitted when left is completed
        Flux<String> fluxConcat = flux1.concatWith(flux2);
        Flux<String> fluxConcat2 = Flux.concat(flux1, flux2);
        Flux<String> fluxConcat3 = Flux.concat(flux1, flux2, flux3);
        // throws error before emitting flux3 (LAZY)
        Flux<String> fluxConcatError = Flux.concat(flux1, flux2, fluxError, flux3);
        // throws error after emitting all items due to DelayError
        Flux<String> fluxConcatErrorDelayed = Flux.concatDelayError(flux1, flux2, fluxError, flux3);

        fluxConcat.subscribe(Util.subscriber());
        fluxConcat2.subscribe(Util.subscriber());
        fluxConcat3.subscribe(Util.subscriber());
        fluxConcatError.subscribe(Util.subscriber());
        fluxConcatErrorDelayed.subscribe(Util.subscriber());

    }

}
