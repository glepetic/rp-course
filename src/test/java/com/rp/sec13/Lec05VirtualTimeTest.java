package com.rp.sec13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Supplier;

public class Lec05VirtualTimeTest {

    @Test
    public void test1() {

        // Assemble
        Supplier<Flux<String>> testInputSupplier = this::timeConsumingFlux;

        // Act (Subscribe) + Assert
        StepVerifier.withVirtualTime(testInputSupplier)
                .thenAwait(Duration.ofSeconds(30)) // assuming 30 seconds have passed -> expects...
                .expectNext("1 finished", "2 finished", "3 finished", "4 finished")
                .verifyComplete();

    }

    @Test
    public void test2() {

        // Assemble
        Supplier<Flux<String>> testInputSupplier = this::timeConsumingFlux;

        // Act (Subscribe) + Assert
        StepVerifier.withVirtualTime(testInputSupplier)
                .expectSubscription() // subscription is an event
                .expectNoEvent(Duration.ofSeconds(4))
                .thenAwait(Duration.ofSeconds(20))
                .expectNext("1 finished", "2 finished", "3 finished", "4 finished")
                .verifyComplete();

    }

    private Flux<String> timeConsumingFlux() {
        return Flux.range(1, 4)
                .delayElements(Duration.ofSeconds(5))
                .map(i -> i + " finished");
    }

}
