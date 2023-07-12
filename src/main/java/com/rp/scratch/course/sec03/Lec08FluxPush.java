package com.rp.scratch.course.sec03;

import com.rp.scratch.course.sec03.helper.NameProducer;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec08FluxPush {

    public static void main(String[] args) throws InterruptedException {

        // THIS IS NOT THREAD SAFE, works like flux.create(), but should be used  signle-threaded

        NameProducer nameProducer = new NameProducer();

        Flux.push(nameProducer)
                .subscribe(ScratchUtil.subscriber());

        Runnable producer = nameProducer::produce;

        // inconsistent output with multi-threading
        for (int i = 0; i < 10; i++) {
            new Thread(producer).start();
        }

        ScratchUtil.sleepSeconds(2);

    }

}
