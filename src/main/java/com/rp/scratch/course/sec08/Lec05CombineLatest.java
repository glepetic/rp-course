package com.rp.scratch.course.sec08;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05CombineLatest {

    public static void main(String[] args) {

        Flux.combineLatest(getString(), getNumber(), (str, num) -> str + num)
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(10);

    }

    private static Flux<String> getString() {
        return Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getNumber() {
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(3));
    }

}
