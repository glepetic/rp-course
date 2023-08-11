package com.rp.sec13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec06ScenarioNameTest {

    @Test
    public void test1() {

        // Assemble
        Flux<String> testInput = Flux.just("a", "b", "c");
        StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("alphabets-test");

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput, scenarioName)
                .expectNextCount(12)
                .verifyComplete();

    }

    @Test
    public void test2() {

        // Assemble
        Flux<String> testInput = Flux.just("a", "b1", "c");

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .expectNext("a")
                .as("a-test")
                .expectNext("b")
                .as("b-test")
                .expectNext("c")
                .as("c-test")
                .verifyComplete();

    }

}
