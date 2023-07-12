package com.rp.scratch.course.sec03;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec03FluxTake {

    public static void main(String[] args) {

        /*
        Take is an opertaor, just like map and filter
        Take receives a number (N) and cancels the subscription once it has processed/emitted N items
         */
        Flux.range(0, 10)
                .log()
                .take(3)
                .log()
                .subscribe(ScratchUtil.subscriber());

    }

}
