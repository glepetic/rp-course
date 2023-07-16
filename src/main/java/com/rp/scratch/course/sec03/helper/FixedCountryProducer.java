package com.rp.scratch.course.sec03.helper;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class FixedCountryProducer implements Consumer<FluxSink<String>> {

    private FixedCountryProducer() {}

    public static FixedCountryProducer instance() {
        return new FixedCountryProducer();
    }

    @Override
    public void accept(FluxSink<String> fluxSink) {
        String country;
        do country = this.produce(fluxSink);
        while (!country.equalsIgnoreCase("Canada") && !fluxSink.isCancelled());
        fluxSink.complete();
    }

    public String produce(FluxSink<String> fluxSink) {
        String country = ScratchUtil.faker().country().name();
        System.out.println("Emitting: " + country);
        fluxSink.next(country);
        return country;
    }

}
