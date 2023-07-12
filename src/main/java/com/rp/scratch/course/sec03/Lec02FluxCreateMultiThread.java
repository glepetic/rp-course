package com.rp.scratch.course.sec03;

import com.rp.scratch.course.sec03.helper.NameProducer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateMultiThread {

    public static void main(String[] args) throws InterruptedException {

        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(ScratchUtil.subscriber());

        Runnable producer = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(producer).start();
        }

        ScratchUtil.sleepSeconds(2);

    }

}
