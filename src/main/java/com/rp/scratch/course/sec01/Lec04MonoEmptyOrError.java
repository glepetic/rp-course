package com.rp.scratch.course.sec01;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {

    public static void main(String[] args) {

        userRepository(1)
                .subscribe(
                        ScratchUtil.onNextPrintValue(),
                        ScratchUtil.onErrorPrintMessage(),
                        ScratchUtil.onCompletePrintCompleted()
                );

        userRepository(2)
                .subscribe(
                        ScratchUtil.onNextPrintValue(),
                        ScratchUtil.onErrorPrintMessage(),
                        ScratchUtil.onCompletePrintCompleted()
                );

        userRepository(3)
                .subscribe(
                        ScratchUtil.onNextPrintValue(),
                        ScratchUtil.onErrorPrintMessage(),
                        ScratchUtil.onCompletePrintCompleted()
                );

    }

    private static Mono<String> userRepository(int userId) {

        if (userId == 1) {
            return Mono.just(ScratchUtil.faker().name().firstName());
        } else if (userId == 2) {
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Not in the allowed range"));
        }

    }

}
