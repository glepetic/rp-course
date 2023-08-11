package com.rp.sec13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec02SVErrorTest {

    @Test
    public void test1() {

        // Assemble
        Flux<Integer> testInput = this.getFlux();

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .expectNext(1, 2, 3)
                .verifyError();

    }

    @Test
    public void test2() {

        // Assemble
        Flux<Integer> testInput = this.getFlux();

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .expectNext(1, 2, 3)
                .verifyError(RuntimeException.class);

    }

    @Test
    public void test3() {

        // Assemble
        Flux<Integer> testInput = this.getFlux();

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .expectNext(1, 2, 3)
                .verifyErrorMessage("oops");

    }

    @Test
    public void test4() {

        // Assemble
        Flux<Integer> testInput = this.getFlux();

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .expectNext(1, 2, 3)
                .verifyErrorMatches(err -> err instanceof RuntimeException && err.getMessage().equals("oops"));

    }

    private Flux<Integer> getFlux() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        Flux<Integer> error = Flux.error(new RuntimeException("oops"));
        return Flux.concat(flux, error);
    }

}
