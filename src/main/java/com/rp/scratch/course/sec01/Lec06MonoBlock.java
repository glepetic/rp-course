package com.rp.scratch.course.sec01;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec06MonoBlock {

    public static void main(String[] args) {

        // simply BUILDS pipeline
        getName();

        // blocking because getName uses sleep on main thread
        getName()
                // subscribe makes it so you EXECUTE the pipeline
                .subscribe(
                        ScratchUtil.onNextPrintValue()
                );

        // non-blocking because getName is being subscribed in a different thread
        getName()
                //used to make it NON blocking subscribe, subscribes in a different thread, not main thread
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        ScratchUtil.onNextPrintValue()
                );

        // blocking because it has block() method
        // block method should only be used for testing/demo purposes, not for application logic
        String blockedResult = getName()
                .block();

        System.out.println("Blocked result: " + blockedResult);

        // main thread sleep to make sure all threads finished, this won't happen on a real application
        ScratchUtil.sleepSeconds(4);

    }

    // returning this causes you to BUILD the pipeline, not execute it
    // building is simple and fast, executing is time-consuming (business logic)
    private static Mono<String> getName() {
        System.out.println("Entered getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name..");
            ScratchUtil.sleepSeconds(3);
            return ScratchUtil.faker().name().fullName();
        })
                .map(String::toUpperCase);
    }

}
