package com.rp.scratch.course.sec05;

import com.rp.scratch.course.sec05.helper.MovieStreamer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec03HotPublish {

    public static void main(String[] args) {

        // share = publish().refCount(1)
        Flux<String> movieStream = MovieStreamer.instance()
                .stream()
                .publish()
                // 2 minimum subscribers to emit
                .refCount(2);

        movieStream
                .doFirst(() -> System.out.println("Sam is joining to watch"))
                .subscribe(ScratchUtil.subscriber("Sam"));

        ScratchUtil.sleepSeconds(3);

        movieStream
                .doFirst(() -> System.out.println("Mike is joining to watch"))
                .subscribe(ScratchUtil.subscriber("Mike"));

        ScratchUtil.sleepSeconds(60);

    }

}
