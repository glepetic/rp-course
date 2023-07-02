package com.rp.course.sec05;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lec03HotPublishReset {

    public static void main(String[] args) {

        // share = publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(Lec03HotPublishReset::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(1);

        movieStream
                .subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(3);

        movieStream
                .subscribe(Util.subscriber("Paul"));

        Util.sleepSeconds(10);

        movieStream
                .subscribe(Util.subscriber("Mike"));

        Util.sleepSeconds(60);

    }
    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming request");
        return IntStream.range(1, 8)
                .mapToObj(i -> "Scene " + i);
    }

}
