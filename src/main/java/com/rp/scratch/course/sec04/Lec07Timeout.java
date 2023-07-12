package com.rp.scratch.course.sec04;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Timeout {

    public static void main(String[] args) {

      getOrderNumbers()
              .timeout(Duration.ofSeconds(2), fallback())
              .subscribe(ScratchUtil.subscriber());

      ScratchUtil.sleepSeconds(60);

    }

    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5));
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 10)
                .delayElements(Duration.ofMillis(200));
    }

}
