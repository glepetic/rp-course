package com.rp.course.sec01;

import com.rp.util.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {

    public static void main(String[] args) {

        userRepository(1)
                .subscribe(
                        Util.onNextPrintValue(),
                        Util.onErrorPrintMessage(),
                        Util.onCompletePrintCompleted()
                );

        userRepository(2)
                .subscribe(
                        Util.onNextPrintValue(),
                        Util.onErrorPrintMessage(),
                        Util.onCompletePrintCompleted()
                );

        userRepository(3)
                .subscribe(
                        Util.onNextPrintValue(),
                        Util.onErrorPrintMessage(),
                        Util.onCompletePrintCompleted()
                );

    }

    private static Mono<String> userRepository(int userId) {

        if (userId == 1) {
            return Mono.just(Util.faker().name().firstName());
        } else if (userId == 2) {
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Not in the allowed range"));
        }

    }

}
