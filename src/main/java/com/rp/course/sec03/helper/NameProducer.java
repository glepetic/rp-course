package com.rp.course.sec03.helper;

import com.rp.util.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> fluxSink) {
        this.fluxSink = fluxSink;
    }

    public void produce() {
        String country = Util.faker().country().name();
        String thread = Thread.currentThread().getName();
        this.fluxSink.next(thread + ": " + country);
    }

}
