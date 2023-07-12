package com.rp.scratch.course.sec02;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec09MonoFromFlux {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .filter(i -> i > 3)
                // returns first value found
                .next()
                .subscribe(ScratchUtil.onNextPrintValue(), ScratchUtil.onErrorPrintMessage(), ScratchUtil.onCompletePrintCompleted());

        Flux.range(1, 10)
                // gets all values as a list
                .collectList()
                .subscribe(ScratchUtil.onNextPrintValue(), ScratchUtil.onErrorPrintMessage(), ScratchUtil.onCompletePrintCompleted());

    }

}
