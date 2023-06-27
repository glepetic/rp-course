package com.rp.course.sec03;

import com.rp.course.sec03.helper.SyncCountryProducer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerateAssignment {

    public static void main(String[] args) {

        Flux.generate(SyncCountryProducer.instance())
                .subscribe(Util.subscriber());

    }

}
