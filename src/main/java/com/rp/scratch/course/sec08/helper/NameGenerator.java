package com.rp.scratch.course.sec08.helper;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NameGenerator {

    private static NameGenerator instance = null;

    private final List<String> cacheNames;

    private NameGenerator() {
        this.cacheNames = new ArrayList<>();
    }

    public static NameGenerator instance() {
        return instance = Objects.isNull(instance) ? new NameGenerator() : instance;
    }

    public Flux<String> generateNames() {
        return Flux.generate((SynchronousSink<String> sink) -> {
            System.out.println("Generating");
            ScratchUtil.sleepSeconds(1);
            String name = ScratchUtil.faker().name().firstName();
            this.cacheNames.add(name);
            System.out.println("Generated " + name);
            sink.next(name);
        })
                .startWith(this.getFromCache());
    }

    private Flux<String> getFromCache() {
        return Flux.fromIterable(this.cacheNames);
    }

}
