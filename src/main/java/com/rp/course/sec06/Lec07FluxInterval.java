package com.rp.course.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07FluxInterval {

    public static void main(String[] args) {

        // main ends because Flux.interval() uses Schedulers.parallel() internally
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(v -> Util.printThreadName("sub " + v));

        // main ends because Flux.delayElements() uses Schedulers.parallel() internally
        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(10))
                .subscribe(v -> Util.printThreadName("sub " + v));

        // cannot be modified with publishOn or subscribeOn, closest to subscriber takes precedence

    }


}
