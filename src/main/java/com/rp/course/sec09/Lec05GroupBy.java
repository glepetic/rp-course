package com.rp.course.sec09;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05GroupBy {

    public static void main(String[] args) {

           Flux.range(0, 30)
                   .delayElements(Duration.ofSeconds(1))
                   .groupBy(i -> i % 2)
                   .subscribe(gf -> process(gf, gf.key()));

           Util.sleepSeconds(60);

    }
    private static void process(Flux<Integer> flux, int key) {
        System.out.println("Called"); // gets called N times, N being the amount of groups (here it's 2)
        flux.subscribe(sum -> System.out.println("Key: " + key + ", Item: " + sum));
    }

}
