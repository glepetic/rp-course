package com.rp.scratch.course.sec03;

import com.rp.scratch.course.sec03.helper.FixedCountryProducer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateIssueFix {

    public static void main(String[] args) {

        Flux.create(FixedCountryProducer.instance())
                .take(3)
                .subscribe(ScratchUtil.subscriber());

    }

}
