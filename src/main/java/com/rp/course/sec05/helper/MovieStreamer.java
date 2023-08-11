package com.rp.course.sec05.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MovieStreamer {

    private static MovieStreamer instance = null;

    private MovieStreamer() {}

    public static MovieStreamer instance() {
        return instance = Objects.isNull(instance) ? new MovieStreamer() : instance;
    }
    public Flux<String> stream() {
        return Flux.fromStream(this::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .doOnComplete(() -> System.out.println("Movie Ended"));
    }

    private Stream<String> getMovie() {
        System.out.println("Movie Started");
        return IntStream.range(1, 8)
                .mapToObj(i -> "Scene " + i);
    }

}
