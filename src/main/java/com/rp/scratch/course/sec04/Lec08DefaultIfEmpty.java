package com.rp.scratch.course.sec04;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec08DefaultIfEmpty {

    public static void main(String[] args) {

        getOrderNumbers()
                .filter(i -> i > 10)
                // EAGER -> receives a fallback value for empty scenario
                .defaultIfEmpty(-100)
                .subscribe(ScratchUtil.subscriber());

    }

    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }

}
