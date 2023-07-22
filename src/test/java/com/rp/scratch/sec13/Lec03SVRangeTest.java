package com.rp.scratch.sec13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03SVRangeTest {

    @Test
    public void test1() {

        // Assemble
        Flux<Integer> testInput = Flux.range(1, 50);

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .expectNextCount(50)
                .verifyComplete();

    }

    @Test
    public void test2() {

        // Assemble
        Flux<Integer> testInput = Flux.range(1, 50);

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .thenConsumeWhile(i -> i < 51)
                .verifyComplete();

    }

}
