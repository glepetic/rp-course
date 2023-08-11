package com.rp.course.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08FluxInterval {

    public static void main(String[] args) {

        // used for periodic or scheduled tasks
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(i -> System.out.println("Iteration #" + (i+1)));

        // must sleep because interval is not in main thread (uses different thread pool)
        Util.sleepSeconds(5);

    }

}
