package com.rp.course.sec12.helper;

import reactor.core.publisher.Mono;

import java.util.Objects;

public class Welcome {

    private static Welcome instance = null;

    private Welcome() {}

    public static Welcome instance() {
        return instance = Objects.isNull(instance) ? new Welcome() : instance;
    }

    public Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> Mono.just(ctx)
                .filter(context -> context.hasKey("user"))
                .map(context -> "Welcome " + context.get("user"))
                .switchIfEmpty(Mono.error(() -> new RuntimeException("unauthenticated")))
        );
    }

    public Mono<String> getWelcomeMessageWithDefault() {
        return Mono.deferContextual(ctx -> Mono.just("Welcome " + ctx.getOrDefault("user", "anon")));
    }

}
