package com.rp.scratch.course.sec01;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec07MonoFromFuture {

    public static void main(String[] args) {

        Mono<String> mono = Mono.fromFuture(Lec07MonoFromFuture::getName);
        mono.subscribe(
                ScratchUtil.onNextPrintValue()
        );

        ScratchUtil.sleepSeconds(1);

    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> ScratchUtil.faker().name().fullName());
    }

}
