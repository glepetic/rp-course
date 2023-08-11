package com.rp.sec13;

import com.rp.course.sec12.helper.Welcome;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec07ContextTest {

    @Test
    public void test1() {

        // Assemble
        Welcome welcome = Welcome.instance();

        // Act (subscribe) + Assert
        StepVerifier.create(welcome.getWelcomeMessage())
                .verifyErrorMatches(err -> err instanceof RuntimeException && err.getMessage().equals("unauthenticated"));

    }

    @Test
    public void test2() {

        // Assemble
        Welcome welcome = Welcome.instance();
        StepVerifierOptions options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"));

        // Act (subscribe) + Assert
        StepVerifier.create(welcome.getWelcomeMessage(), options)
                .expectNext("Welcome sam")
                .verifyComplete();

    }

}
