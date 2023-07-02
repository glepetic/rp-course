package com.rp.course.sec03;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {

        // only one instance of fluxSink
        Flux.create(fluxSink -> fluxSink
                        .next(1)
                        .next(2)
                        .complete()
                )
                .subscribe(Util.subscriber("Emit"));

        // emits countries until it emits Canada or it does 10 iterations
        Flux.create(fluxSink -> {
                    int counter = 0;
                    String country;
                    do {
                        counter++;
                        country = Util.faker().country().name();
                        fluxSink.next(country);
                    } while (!country.equalsIgnoreCase("Canada") && counter < 10);
                    fluxSink.complete();
                })
                .subscribe(Util.subscriber("EmitUntil"));

    }

}
