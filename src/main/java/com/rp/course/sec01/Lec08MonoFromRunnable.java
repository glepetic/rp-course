package com.rp.course.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

public class Lec08MonoFromRunnable {

    public static void main(String[] args) {

        Mono.fromRunnable(Lec08MonoFromRunnable::timeConsumingProcess)
                .subscribe(
                        // this never executes because fromRunnable returns Mono<Void>
                        Util.onNextPrintValue(),
                        Util.onErrorPrintMessage(),
                        // fromRunnable is used for the onComplete task, to chain an action
                        () -> System.out.println("Process is done, sending emails...")
                );

    }

    private static void timeConsumingProcess() {
        Util.sleepSeconds(3);
        System.out.println("Operation completed");
    }

}
