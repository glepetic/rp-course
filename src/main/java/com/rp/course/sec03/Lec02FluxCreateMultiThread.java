package com.rp.course.sec03;

import com.rp.course.sec03.helper.NameProducer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateMultiThread {

    public static void main(String[] args) throws InterruptedException {

        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(Util.subscriber());

        Runnable producer = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(producer).start();
        }

        Util.sleepSeconds(2);

    }

}
