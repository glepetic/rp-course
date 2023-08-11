package com.rp.course.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class Lec02HandleAssignment {

    public static void main(String[] args) {

        Flux.generate((SynchronousSink<String> synchronousSink) -> synchronousSink.next(Util.faker().country().name()))
                .handle((country, sink) -> {
                    sink.next(country);
                    if (country.equalsIgnoreCase("Argentina")) sink.complete();
                })
                .subscribe(Util.subscriber());


    }

}
