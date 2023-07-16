package com.rp.scratch.course.sec02;

import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux.subscribe(i -> System.out.println("Sub 1 all numbers: " + i));

        flux
                .filter(i -> i % 2 > 0)
                .subscribe(i -> System.out.println("Sub 2 only odd numbers: " + i));

        flux
                .filter(i -> i % 2 == 0)
                .subscribe(i -> System.out.println("Sub 3 only even numbers: " + i));

    }

}
