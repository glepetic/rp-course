package com.rp.course.sec09;

import com.rp.course.sec09.helper.EventStreamer;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01BufferTimeout {

    private static final EventStreamer eventStreamer = EventStreamer.instance();

    public static void main(String[] args) {

        // emits whatever amount it can every 2 seconds, can cause too many elements emitted at once
        highEventStream()
                .buffer(Duration.ofSeconds(2))
                .subscribe(Util.subscriber("Every 2 Seconds No Limit"));

        // double condition, MAX 5 elements, but emits onNext if it didn't gather enough in the time given
        highEventStream()
                .bufferTimeout(5, Duration.ofSeconds(2))
                .subscribe(Util.subscriber("Every 2 Seconds With Limit"));

        lowEventStream()
                .bufferTimeout(5, Duration.ofSeconds(2))
                .subscribe(Util.subscriber("Low Event Stream"));

        Util.sleepSeconds(60);

    }

    private static Flux<String> highEventStream() {
        return eventStreamer.eventStream(10);
    }

    private static Flux<String> lowEventStream() {
        return eventStreamer.eventStream(800);
    }



}
