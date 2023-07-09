package com.rp.course.sec05;

import com.rp.course.sec05.helper.MovieStreamer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec04HotPublishNoSubscribers {

    public static void main(String[] args) {

        Flux<String> movieStream = MovieStreamer.instance()
                .stream()
                .publish()
                // starts emitting without subscribers
                .autoConnect(0);

        Util.sleepSeconds(5);

        movieStream
                .doFirst(() -> System.out.println("Sam is joining to watch"))
                .subscribe(Util.subscriber("Sam"));

        movieStream
                .doFirst(() -> System.out.println("Paul is joining to watch"))
                .subscribe(Util.subscriber("Paul"));

        Util.sleepSeconds(5);

        movieStream
                .doFirst(() -> System.out.println("Mary is joining to watch"))
                .subscribe(Util.subscriber("Mary"));

        System.out.println("Mary was too late, there is nothing to watch");

        Util.sleepSeconds(10);


    }

}
