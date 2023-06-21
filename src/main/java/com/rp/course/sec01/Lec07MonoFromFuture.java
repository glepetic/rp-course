package com.rp.course.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec07MonoFromFuture {

    public static void main(String[] args) {

        Mono<String> mono = Mono.fromFuture(Lec07MonoFromFuture::getName);
        mono.subscribe(
                Util.onNextPrintValue()
        );

        Util.sleepSeconds(1);

    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }

}
