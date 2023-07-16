package com.rp.scratch.course.sec01;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Mono;

public class Lec08MonoFromRunnable {

    public static void main(String[] args) {

        Mono.fromRunnable(Lec08MonoFromRunnable::timeConsumingProcess)
                .subscribe(
                        // this never executes because fromRunnable returns Mono<Void>
                        ScratchUtil.onNextPrintValue(),
                        ScratchUtil.onErrorPrintMessage(),
                        // fromRunnable is used for the onComplete task, to chain an action
                        () -> System.out.println("Process is done, sending emails...")
                );

    }

    private static void timeConsumingProcess() {
        ScratchUtil.sleepSeconds(3);
        System.out.println("Operation completed");
    }

}
