package com.rp.scratch.course.sec08.helper;

import reactor.core.publisher.Flux;

import java.util.Objects;

public class ComponentsGenerator {

    private static ComponentsGenerator instance = null;

    private ComponentsGenerator() {}

    public static ComponentsGenerator instance() {
        return instance = Objects.isNull(instance) ? new ComponentsGenerator() : instance;
    }

    public Flux<String> getBody() {
        return Flux.range(1,5)
                .map(i -> "body");
    }

    public Flux<String> getEngine() {
        return Flux.range(1,2)
                .map(i -> "engine");
    }

    public Flux<String> getTires() {
        return Flux.range(1,6)
                .map(i -> "tires");
    }

}
