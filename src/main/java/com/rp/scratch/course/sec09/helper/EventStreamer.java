package com.rp.scratch.course.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class EventStreamer {

    private static EventStreamer instance = null;

    private EventStreamer() {
    }

    public static EventStreamer instance() {
        return instance = Objects.isNull(instance) ? new EventStreamer() : instance;
    }

    public Flux<String> eventStream(int rate) {
        return Flux.interval(Duration.ofMillis(rate))
                .map(i -> "event " + i);
    }

}
