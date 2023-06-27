package com.rp.course.sec03.helper;

import com.rp.util.Util;
import reactor.core.publisher.SynchronousSink;

import java.util.function.Consumer;

public class SyncCountryProducer implements Consumer<SynchronousSink<String>> {

    private SyncCountryProducer() {}

    public static SyncCountryProducer instance() {
        return new SyncCountryProducer();
    }

    @Override
    public void accept(SynchronousSink<String> sink) {
        String country = this.produce(sink);
        if (country.equalsIgnoreCase("Canada")) sink.complete();
    }

    private String produce(SynchronousSink<String> sink) {
        String country = Util.faker().country().name();
        System.out.println("Emitting: " + country);
        sink.next(country);
        return country;
    }

}
