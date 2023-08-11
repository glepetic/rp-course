package com.rp.course.sec05;

import com.rp.course.sec05.helper.MovieStreamer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec04HotPublishAutoConnect {

    public static void main(String[] args) {

        Flux<String> movieStream = MovieStreamer.instance()
                .stream()
                .publish()
                // emits items only once per connected subscriber, does not restart
                .autoConnect(1);

        movieStream
                .doFirst(() -> System.out.println("Sam is joining to watch"))
                .subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(3);

        movieStream
                .doFirst(() -> System.out.println("Paul is joining to watch"))
                .subscribe(Util.subscriber("Paul"));

        Util.sleepSeconds(10);

        movieStream
                .doFirst(() -> System.out.println("Mike is joining to watch"))
                .subscribe(Util.subscriber("Mike"));

        System.out.println("Mike was too late, there is nothing to watch");

        Util.sleepSeconds(60);

    }

}
