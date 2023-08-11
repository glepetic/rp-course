package com.rp.course.sec05;

import com.rp.course.sec05.helper.MovieStreamer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec02HotShare {

    public static void main(String[] args) {

        Flux<String> movieStream = MovieStreamer.instance()
                .stream()
                .share();

        movieStream
                .doFirst(() -> System.out.println("Sam is joining to watch"))
                .subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(3);

        movieStream
                .doFirst(() -> System.out.println("Mike is joining to watch"))
                .subscribe(Util.subscriber("Mike"));

        Util.sleepSeconds(60);

    }

}
