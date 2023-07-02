package com.rp.course.sec05;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lec01ColdPublisher {

    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(Lec01ColdPublisher::getMovie)
                .delayElements(Duration.ofSeconds(1));

        movieStream
                .subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(5);

        movieStream
                .subscribe(Util.subscriber("Mike"));

        Util.sleepSeconds(60);

    }

    // Netflix
    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming request");
        return IntStream.range(1, 8)
                .mapToObj(i -> "Scene " + i);
    }

}
