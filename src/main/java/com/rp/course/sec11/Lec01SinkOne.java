package com.rp.course.sec11;

import com.rp.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec01SinkOne {

    public static void main(String[] args) {

        // can emit 1 value | empty | error (like Mono)
        Sinks.One<Object> sink = Sinks.one();

        Mono<Object> mono = sink.asMono();

        mono.subscribe(Util.subscriber("Value1Sub"));
        mono.subscribe(Util.subscriber("Value2Sub"));

        // sends complete signal automatically after emitting one item because it's a "One Sink" or Mono
        sink.tryEmitValue("hi");

        // empty example
        Sinks.One<Object> sink2 = Sinks.one();
        sink2.tryEmitEmpty();
        Mono<Object> mono2 = sink2.asMono();
        mono2.subscribe(Util.subscriber("EmptySub"));

        // error example
        Sinks.One<Object> sink3 = Sinks.one();
        sink3.tryEmitError(new RuntimeException("oops"));
        Mono<Object> mono3 = sink3.asMono();
        mono3.subscribe(Util.subscriber("ErrorSub"));

        // fallback for retry
        Sinks.One<Object> sinkRetry = Sinks.one();

        Mono<Object> monoRetry = sinkRetry.asMono();

        monoRetry.subscribe(Util.subscriber("RetrySub"));

        // won't execute fallback because it doesn't have an error
        sinkRetry.emitValue("hi", ((signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false; // true -> retry, false -> no retry
        }));

        AtomicInteger retryCount = new AtomicInteger(1);

        // executes fallback because Mono can only emit 1 or 0 items
        sinkRetry.emitValue("hello", ((signalType, emitResult) -> {
            System.out.println("Retry attempt " + retryCount.get() + ": " + signalType.name());
            System.out.println("Retry attempt " + retryCount.get() + ": " + emitResult.name());
            return retryCount.getAndIncrement() < 3; // true -> retry, false -> no retry
        }));

    }

}
