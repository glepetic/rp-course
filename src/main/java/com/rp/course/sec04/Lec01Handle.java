package com.rp.course.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {

    public static void main(String[] args) {

        // handle = filter + map -> push the data yourself
        Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    if (integer % 2 == 0) synchronousSink.next(integer * 5);
                })
                .subscribe(Util.subscriber("Handle"));

        Flux.range(1, 20)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 5)
                .subscribe(Util.subscriber("FilterMap"));


    }

}
