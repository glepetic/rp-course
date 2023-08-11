package com.rp.course.sec03;

import com.rp.course.sec03.helper.FixedCountryProducer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateIssueFix {

    public static void main(String[] args) {

        Flux.create(FixedCountryProducer.instance())
                .take(3)
                .subscribe(Util.subscriber());

    }

}
