package com.rp.scratch.course.sec03.helper;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class CountryProducer implements Consumer<FluxSink<String>> {

    private CountryProducer() {}

    public static CountryProducer instance() {
        return new CountryProducer();
    }

    @Override
    public void accept(FluxSink<String> fluxSink) {
        String country;
        do country = this.produce(fluxSink);
        while (!country.equalsIgnoreCase("Canada"));
        fluxSink.complete();
    }

    public String produce(FluxSink<String> fluxSink) {
        String country = ScratchUtil.faker().country().name();
        fluxSink.next(country);
        return country;
    }

}
