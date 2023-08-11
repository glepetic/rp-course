package com.rp.course.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {

    public static void main(String[] args) {

        /*
        Subscriber requests for data in bulks
        Once it has consumed/processed 75% of the data, it asks for more.
        For instance, if the limit rate is 100 items, once 75 items have been processed, the susbcriber asks for more
        Works like a buffer/queue of emitted items
        */

        Flux.range(1, 10)
                // requests unbounded, no limit, so it's ALL the data
                .log()
                .subscribe(Util.subscriber("Unbounded"));

        Flux.range(1, 500)
                .log()
                // requests 100, requests 75 more when it finished processing 75
                .limitRate(100)
                .subscribe(Util.subscriber("SubscriberA"));

        Flux.range(1, 1000)
                .log()
                // requests 200, requests 50 more when it finished processing 50
                .limitRate(200, 50)
                .subscribe(Util.subscriber("SubscriberB"));

        Flux.range(1, 500)
                .log()
                // requests 100, requests highTide always
                .limitRate(100, 0)
                .subscribe(Util.subscriber("SubscriberC"));

        int hightide = 32;
        int lowtidePercentage = 75;
        int lowtide = (hightide*lowtidePercentage)/100;

        Flux.range(1, 1000)
                .log()
                /*
                 requests highTide by parameter, requests lowTide calculated as a percentage of highTide
                 when it has processed lowTide amount of elements
                 */
                .limitRate(hightide, lowtide)
                .subscribe(Util.subscriber("PercentageSubscriber"));

    }

}
