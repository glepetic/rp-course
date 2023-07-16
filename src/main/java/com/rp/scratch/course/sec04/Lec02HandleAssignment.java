package com.rp.scratch.course.sec04;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class Lec02HandleAssignment {

    public static void main(String[] args) {

        Flux.generate((SynchronousSink<String> synchronousSink) -> synchronousSink.next(ScratchUtil.faker().country().name()))
                .handle((country, sink) -> {
                    sink.next(country);
                    if (country.equalsIgnoreCase("Argentina")) sink.complete();
                })
                .subscribe(ScratchUtil.subscriber());


    }

}
