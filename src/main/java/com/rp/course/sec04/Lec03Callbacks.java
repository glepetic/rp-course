package com.rp.course.sec04;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Lec03Callbacks {

    public static void main(String[] args) {

        /*
         pipeline execution depends on who is requesting or giving data:
            - doOnFirst: subscriber requests for data so it's upwards
            - doOnSubscribe: publisher provides subscription object to subscriber so it's downwards
         and so on...
         1. doFirst, then there is subscription (doOnSusbcribe), then subscriber requests the data (doOnRequest)
         2. publisher emits onNext or onError signals based on whatever is the next item or event (doOnEach executes in both signals)
         3. when everything is finished emitting or there is an error signal, onComplete/onError/onTerminate are executed
            - onTerminate does NOT execute when subscriber cancels the subscription (for example: take(2)).
            In this case, doOnCancel gets executed instead
         4. at the end, doFinally gets executed
         All items emitted:
            doFirst -> doOnSubscribe -> doOnRequest -> (doOnNext + doOnEach) N times -> doOnComplete + doOnTerminate -> doFinally
         Some items are emitted then error is signaled:
            doFirst -> doOnSubscribe -> doOnRequest -> (doOnNext + doOnEach) N times -> doOnError + doOnEach -> doOnTerminate -> doFinally
         No items are emitted because of an error signal:
            doFirst -> doOnSubscribe -> doOnRequest -> doOnError + doOnEach -> doOnTerminate -> doFinally
         Some items are emitted then subscriber cancels subscription:
            doFirst -> doOnSubscribe -> doOnRequest -> (doOnNext + doOnEach) N times -> doOnCancel -> doFinally
         */
        Flux.create(fluxSink -> {
                    System.out.println("inside create");
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                    // used for doOnError() example
                    // fluxSink.error(new RuntimeException("oops"));
                    System.out.println("--completed");
                })
                .doFirst(() -> System.out.println("doFirst"))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe: " + s))
                .doOnRequest(l -> System.out.println("doOnRequest: " + l))
                .doOnNext(o -> System.out.println("doOnNext: " + o))
                .doOnError(err -> System.out.println("doOnError: " + err.getMessage()))
                .doOnEach(signal -> System.out.println("doOnEach: " + signal))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard: " + o))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doFinally(signal -> System.out.println("doFinally before take: " + signal))
                // take used for doOnDiscard() / doOnCancel() example
                .take(2)
                .doFinally(signal -> System.out.println("doFinally after take: " + signal))
                .subscribe(Util.subscriber());
    }

}
