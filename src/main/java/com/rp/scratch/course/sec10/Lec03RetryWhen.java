package com.rp.scratch.course.sec10;

import com.rp.scratch.course.sec10.helper.NumberProducer;
import com.rp.scratch.util.ScratchUtil;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec03RetryWhen {

    private final static NumberProducer numberProducer = NumberProducer.instance();

    public static void main(String[] args) {

        numberProducer.getRandomErrorNumbers()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(10);

    }

}
