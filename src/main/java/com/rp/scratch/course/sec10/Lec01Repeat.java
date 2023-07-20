package com.rp.scratch.course.sec10;

import com.rp.scratch.course.sec10.helper.NumberProducer;
import com.rp.scratch.util.ScratchUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec01Repeat {

    private final static NumberProducer numberProducer = NumberProducer.instance();
    private final static AtomicInteger atomicInteger = new AtomicInteger(1);

    // Repeat is used to resubscribe after complete signal
    public static void main(String[] args) {

        numberProducer.getNumbers()
                // handles complete signal and resubscribes, subscriber only handles complete when all repetitions executed
                .repeat(2)
                .subscribe(ScratchUtil.subscriber("Repeated"));

        ScratchUtil.sleepSeconds(3);

        // repeats subscription, but different elements are emitted (repeat can be used differently based on use case)
        numberProducer.getIncrementalNumbers(atomicInteger)
                .repeat(2)
                .subscribe(ScratchUtil.subscriber("Incremental"));

    }

}
