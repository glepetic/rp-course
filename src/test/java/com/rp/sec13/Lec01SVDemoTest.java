package com.rp.sec13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec01SVDemoTest {

    @Test
    public void test1() {

        // Assemble
        Flux<Integer> testInput = Flux.just(1, 2, 3);

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();

    }

    @Test
    public void test2() {

        // Assemble
        Flux<Integer> testInput = Flux.just(1, 2, 3);

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .expectNext(1, 2, 3)
                .verifyComplete();

    }

}
