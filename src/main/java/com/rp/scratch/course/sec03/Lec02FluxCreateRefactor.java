package com.rp.scratch.course.sec03;

import com.rp.scratch.course.sec03.helper.CountryProducer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {

    public static void main(String[] args) {

        Flux.create(CountryProducer.instance())
                .subscribe(ScratchUtil.subscriber());

    }

}
