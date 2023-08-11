package com.rp.course.sec05;

import com.rp.course.sec05.helper.MovieStreamer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec05HotPublishCache {

    public static void main(String[] args) {

        // cache = publish().replay()
        // cache stores internally a maximum of Integer.MAX_VALUE items
        Flux<String> movieStream = MovieStreamer.instance()
                .stream()
                .cache();

        Util.sleepSeconds(2);

        movieStream
                .doFirst(() -> System.out.println("Sam joining to watch"))
                .subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(10);

        // mike gets all scenes immediately, no delay of publisher because all items are cached
        movieStream
                .doFirst(() -> System.out.println("Mike joining to watch"))
                .subscribe(Util.subscriber("Mike"));

        Util.sleepSeconds(60);

    }

}
