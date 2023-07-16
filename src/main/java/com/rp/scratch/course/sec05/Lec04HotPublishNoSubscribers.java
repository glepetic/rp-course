package com.rp.scratch.course.sec05;

import com.rp.scratch.course.sec05.helper.MovieStreamer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec04HotPublishNoSubscribers {

    public static void main(String[] args) {

        Flux<String> movieStream = MovieStreamer.instance()
                .stream()
                .publish()
                // starts emitting without subscribers
                .autoConnect(0);

        ScratchUtil.sleepSeconds(5);

        movieStream
                .doFirst(() -> System.out.println("Sam is joining to watch"))
                .subscribe(ScratchUtil.subscriber("Sam"));

        movieStream
                .doFirst(() -> System.out.println("Paul is joining to watch"))
                .subscribe(ScratchUtil.subscriber("Paul"));

        ScratchUtil.sleepSeconds(5);

        movieStream
                .doFirst(() -> System.out.println("Mary is joining to watch"))
                .subscribe(ScratchUtil.subscriber("Mary"));

        System.out.println("Mary was too late, there is nothing to watch");

        ScratchUtil.sleepSeconds(10);


    }

}
