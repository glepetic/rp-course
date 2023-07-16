package com.rp.scratch.course.sec03;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec05FluxGenerate {

    public static void main(String[] args) {

        // Flux.generate() takes care of the loop for you, you don't need to do more than one next() call
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(ScratchUtil.faker().country().name());
                    // wont work because you can emit maximum 1 item with synchronousSink
                    synchronousSink.next(ScratchUtil.faker().country().name());
                })
                .subscribe(ScratchUtil.subscriber("Misuse"));

        // Equivalent of Lec04FluxCreateIssueFix
        Flux.generate(synchronousSink -> {
                    String country = ScratchUtil.faker().country().name();
                    System.out.println("Emitting: " + country);
                    synchronousSink.next(country);
                })
                .take(3) // canceled by subscriber
                .subscribe(ScratchUtil.subscriber("Infinite"));

        // Stops when Canada is emitted, after 10 iterations or after subscriber cancels
        // (not ideal to use outside of scope variable, see Lec07)
        AtomicInteger counter = new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
                    String country = ScratchUtil.faker().country().name();
                    System.out.println("Emitting: " + country);
                    synchronousSink.next(country);
                    if (country.equalsIgnoreCase("Canada") || counter.incrementAndGet() >= 10) synchronousSink.complete();
                })
                .take(3)
                .subscribe(ScratchUtil.subscriber("Until"));

        // Only emits 1 because of complete()
        Flux.generate(synchronousSink -> {
                    String country = ScratchUtil.faker().country().name();
                    System.out.println("Emitting: " + country);
                    synchronousSink.next(country);
                    synchronousSink.complete();
                })
                .take(3)
                .subscribe(ScratchUtil.subscriber("Single"));

        // Only emits 1 because of error()
        Flux.generate(synchronousSink -> {
                    String country = ScratchUtil.faker().country().name();
                    System.out.println("Emitting: " + country);
                    synchronousSink.next(country);
                    synchronousSink.error(new RuntimeException("oops"));
                })
                .take(3)
                .subscribe(ScratchUtil.subscriber("SingleThenError"));

    }

}
