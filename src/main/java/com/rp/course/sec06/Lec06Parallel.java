package com.rp.course.sec06;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Lec06Parallel {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.printThreadName("next " + i))
                .subscribe(v -> Util.printThreadName("sub " + v));

        Util.sleepSeconds(2);

        // not thread safe
        List<Integer> list = new ArrayList<>();

        Flux.range(1, 1000)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(list::add);

        Util.sleepSeconds(2);

        // Size is not 1000 because it's not thread safe
        System.out.println("List size: " + list.size());

        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        Flux.range(1, 1000)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(v -> map.put(v, v));

        Util.sleepSeconds(2);

        // Size is 1000 because it's thread safe
        System.out.println("Map Key size: " + map.keySet().size());

        Util.sleepSeconds(2);

        Flux.range(1, 1000)
                .parallel(2) // parallelism configuration (amount of threads)
                .runOn(Schedulers.parallel())
                .subscribe(v -> Util.printThreadName("sub " + v));

        Util.sleepSeconds(2);

        Flux.range(1, 10)
                .parallel()
                .sequential()
                .subscribe(v -> Util.printThreadName("sub " + v));

        Util.sleepSeconds(2);

    }

}
