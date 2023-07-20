package com.rp.scratch.course.sec10;

import com.rp.scratch.course.sec10.helper.NumberProducer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec01RepeatWithCondition {

    private final static NumberProducer numberProducer = NumberProducer.instance();
    private final static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        // Repeat with no parameter repeats endlessly, unless an error signal gets emitted, then it stops
        numberProducer.getIncrementalNumbersLimited(atomicInteger)
                .repeat()
                .subscribe(ScratchUtil.subscriber("Endless Repeat"));

        ScratchUtil.sleepSeconds(5);
        atomicInteger.set(1);

        // Repeat can take a supplier as a condition to continue
        numberProducer.getIncrementalNumbers(atomicInteger)
                .repeat(() -> atomicInteger.get() < 14)
                .subscribe(ScratchUtil.subscriber("Repeat With Condition"));

    }

}
