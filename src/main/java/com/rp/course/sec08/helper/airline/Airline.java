package com.rp.course.sec08.helper.airline;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Supplier;

public abstract class Airline {

    protected final String name;
    protected Supplier<Integer> flightAmountUpperBound;

    public Airline(String name, Supplier<Integer> supplier) {
        this.name = name.toUpperCase() + " ";
        this.flightAmountUpperBound = supplier;
    }

    public Flux<String> getFlights() {
        return Flux.range(1, flightAmountUpperBound.get())
                .delayElements(Duration.ofSeconds(1))
                .map(i -> name + Util.faker().random().nextInt(100, 999))
                .filter(i -> Util.faker().random().nextBoolean());
    }

}
