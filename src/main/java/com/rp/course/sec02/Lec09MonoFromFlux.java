package com.rp.course.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec09MonoFromFlux {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .filter(i -> i > 3)
                // returns first value found
                .next()
                .subscribe(Util.onNextPrintValue(), Util.onErrorPrintMessage(), Util.onCompletePrintCompleted());

        Flux.range(1, 10)
                // gets all values as a list
                .collectList()
                .subscribe(Util.onNextPrintValue(), Util.onErrorPrintMessage(), Util.onCompletePrintCompleted());

    }

}
