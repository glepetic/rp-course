package com.rp.course.sec03;

import com.rp.course.sec03.helper.CountryProducer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {

    public static void main(String[] args) {

        Flux.create(CountryProducer.instance())
                .subscribe(Util.subscriber());

    }

}
