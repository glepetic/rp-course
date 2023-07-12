package com.rp.scratch.course.sec03;

import com.rp.scratch.course.sec03.helper.SyncCountryProducer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerateAssignment {

    public static void main(String[] args) {

        Flux.generate(SyncCountryProducer.instance())
                .subscribe(ScratchUtil.subscriber());

    }

}
