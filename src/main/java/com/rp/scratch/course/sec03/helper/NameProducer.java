package com.rp.scratch.course.sec03.helper;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> fluxSink) {
        this.fluxSink = fluxSink;
    }

    public void produce() {
        String country = ScratchUtil.faker().country().name();
        String thread = Thread.currentThread().getName();
        this.fluxSink.next(thread + ": " + country);
    }

}
