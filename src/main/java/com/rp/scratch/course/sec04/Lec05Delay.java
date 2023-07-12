package com.rp.scratch.course.sec04;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Delay {

    public static void main(String[] args) {

        // defines default buffer to limit publish rate for delayed elements
        System.setProperty("reactor.bufferSize.x", "9");

        Flux.range(1, 100)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(105);

    }

}
