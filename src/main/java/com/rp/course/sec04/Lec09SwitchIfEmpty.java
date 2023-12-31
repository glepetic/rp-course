package com.rp.course.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec09SwitchIfEmpty {

    public static void main(String[] args) {

        getOrderNumbers()
                .filter(i -> i > 10)
                /*
                 LAZY in some newer reactor versions
                 EAGER in older ones, can use Mono.defer() to give it a LAZY behavior
                 */
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }

    private static Flux<Integer> fallback() {
        return Flux.defer(() -> Flux.range(20, 5));
    }

}
