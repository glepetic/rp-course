package com.rp.scratch.course.sec01;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Mono;

public class Lec05MonoFromSupplier {

    public static void main(String[] args) {

        // use just only when you already have the data, here it's calling getName() eagerly which we don't want
        Mono<String> mono = Mono.just(getName());

        // use supplier when data has to be generated
        Mono<String> mono2 = Mono.fromSupplier(Lec05MonoFromSupplier::getName);
        mono2.subscribe(
                ScratchUtil.onNextPrintValue()
        );

        // works like supplier, but callable is used to indicate that the code will be executed in a different thread
        Mono<String> mono3 = Mono.fromCallable(Lec05MonoFromSupplier::getName);
        mono3.subscribe(
                ScratchUtil.onNextPrintValue()
        );

    }

    private static String getName() {
        System.out.println("Generating name..");
        return ScratchUtil.faker().name().fullName();
    }

}
