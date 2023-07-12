package com.rp.scratch.course.sec05;

import com.rp.scratch.course.sec05.helper.MovieStreamer;
import com.rp.scratch.util.ScratchUtil;
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
                .subscribe(ScratchUtil.subscriber("Sam"));

        ScratchUtil.sleepSeconds(3);

        movieStream
                .doFirst(() -> System.out.println("Paul is joining to watch"))
                .subscribe(ScratchUtil.subscriber("Paul"));

        ScratchUtil.sleepSeconds(10);

        movieStream
                .doFirst(() -> System.out.println("Mike is joining to watch"))
                .subscribe(ScratchUtil.subscriber("Mike"));

        System.out.println("Mike was too late, there is nothing to watch");

        ScratchUtil.sleepSeconds(60);

    }

}
