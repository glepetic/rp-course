package com.rp.course.sec10;

import com.rp.course.sec10.helper.NumberProducer;
import com.rp.util.Util;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03RetryWhen {

    private final static NumberProducer numberProducer = NumberProducer.instance();

    public static void main(String[] args) {

        numberProducer.getRandomErrorNumbers()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }

}
