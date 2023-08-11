package com.rp.course.sec10;

import com.rp.course.sec10.helper.NumberProducer;
import com.rp.util.Util;

public class Lec02Retry {

    private final static NumberProducer numberProducer = NumberProducer.instance();

    public static void main(String[] args) {

        numberProducer.getRandomErrorNumbers()
                .retry(2)
                .subscribe(Util.subscriber());

    }

}
