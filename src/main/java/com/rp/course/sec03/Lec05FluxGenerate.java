package com.rp.course.sec03;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec05FluxGenerate {

    public static void main(String[] args) {

        // Flux.generate() takes care of the loop for you, you don't need to do more than one next() call
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(Util.faker().country().name());
                    // wont work because you can emit maximum 1 item with synchronousSink
                    synchronousSink.next(Util.faker().country().name());
                })
                .subscribe(Util.subscriber("Misuse"));

        // Equivalent of Lec04FluxCreateIssueFix
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    System.out.println("Emitting: " + country);
                    synchronousSink.next(country);
                })
                .take(3) // canceled by subscriber
                .subscribe(Util.subscriber("Infinite"));

        // Stops when Canada is emitted, after 10 iterations or after subscriber cancels
        // (not ideal to use outside of scope variable, see Lec07)
        AtomicInteger counter = new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    System.out.println("Emitting: " + country);
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("Canada") || counter.incrementAndGet() >= 10) synchronousSink.complete();
                })
                .take(3)
                .subscribe(Util.subscriber("Until"));

        // Only emits 1 because of complete()
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    System.out.println("Emitting: " + country);
                    synchronousSink.next(country);
                    synchronousSink.complete();
                })
                .take(3)
                .subscribe(Util.subscriber("Single"));

        // Only emits 1 because of error()
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    System.out.println("Emitting: " + country);
                    synchronousSink.next(country);
                    synchronousSink.error(new RuntimeException("oops"));
                })
                .take(3)
                .subscribe(Util.subscriber("SingleThenError"));

    }

}
