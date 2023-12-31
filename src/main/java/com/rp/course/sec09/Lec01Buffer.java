package com.rp.course.sec09;

import com.rp.course.sec09.helper.EventStreamer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec01Buffer {

    private static final EventStreamer eventStreamer = EventStreamer.instance();

    public static void main(String[] args) {

        // collects every N items, in this example the subscriber receives a list of 5 elements with each onNext
        eventStream()
                .buffer(5)
                .subscribe(Util.subscriber("Regular"));

        // when completion signal gets emitted, it gives whatever is left in the buffer even if it's less than N elements
        eventStreamLimited()
                .buffer(5)
                .subscribe(Util.subscriber("Limited"));

        Util.sleepSeconds(60);

    }

    private static Flux<String> eventStream() {
        return eventStreamer.eventStream(300);
    }

    private static Flux<String> eventStreamLimited() {
        return eventStream()
                .take(13);
    }

}
