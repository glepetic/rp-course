package com.rp.course.sec10.helper;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberProducer {

    private static NumberProducer instance = null;

    private NumberProducer() {}

    public static NumberProducer instance() {
        return instance = Objects.isNull(instance) ? new NumberProducer() : instance;
    }

    public Flux<Integer> getNumbers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed: " + s)) // different subscription object each time
                .doOnComplete(() -> System.out.println("--Completed"));
    }

    public Flux<Integer> getIncrementalNumbers(AtomicInteger atomicInteger) {
        return getNumbers()
                .map(i -> atomicInteger.getAndIncrement());

    }

    public Flux<Integer> getIncrementalNumbersLimited(AtomicInteger atomicInteger) {
        return this.getIncrementalNumbers(atomicInteger)
                .flatMap(i -> i > 1000 ? Mono.error(() -> new RuntimeException("oops")) : Mono.just(i));
    }

    public Flux<Integer> getRandomErrorNumbers() {
        return this.getNumbers()
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println("--Error"));
    }

}
