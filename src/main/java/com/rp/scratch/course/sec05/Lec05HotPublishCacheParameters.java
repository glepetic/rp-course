package com.rp.scratch.course.sec05;

import com.rp.scratch.course.sec05.helper.MovieStreamer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec05HotPublishCacheParameters {

    public static void main(String[] args) {

        // cache = publish().replay()
        // cache stores internally a maximum of Integer.MAX_VALUE items
        Flux<String> movieStream = MovieStreamer.instance()
                .stream()
                .cache(2);

        ScratchUtil.sleepSeconds(3);

        movieStream
                .doFirst(() -> System.out.println("Sam joining to watch"))
                .subscribe(ScratchUtil.subscriber("Sam"));

        ScratchUtil.sleepSeconds(10);

        // mike gets only the last 2 scenes because of the parameter received by .cache() method
        movieStream
                .doFirst(() -> System.out.println("Mike joining to watch"))
                .subscribe(ScratchUtil.subscriber("Mike"));

        ScratchUtil.sleepSeconds(60);

    }

}
