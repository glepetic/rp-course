package com.rp.course.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

public class Lec05FluxRange {

    public static void main(String[] args) {

        // The Flux.range(...) works like a for loop

        // 1 -> 10
       Flux.range(1, 10)
               .subscribe(Util.onNextPrintValue());

       // 3 -> 12
        Flux.range(3, 10)
                .subscribe(Util.onNextPrintValue());

        Flux.range(0, 10)
                // useful for debugging/tracing
                .log()
                .map(i -> Util.faker().name().fullName())
                // both log() needed to see the transformation, the original item and the result after map
                .log()
                .subscribe(Util.onNextPrintValue());

    }

}
