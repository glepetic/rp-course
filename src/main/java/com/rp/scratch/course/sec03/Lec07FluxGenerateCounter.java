package com.rp.scratch.course.sec03;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateCounter {

    public static void main(String[] args) {

        // 1st parameter -> supplier with initial state
        // 2nd parameter -> function that receives your current state + sink and returns the updated state
        Flux.generate(
                        () -> 1,
                        (counterState, sink) -> {
                            String country = ScratchUtil.faker().country().name();
                            System.out.println("Emitting: " + country);
                            sink.next(country);
                            if (country.equalsIgnoreCase("Canada") || counterState >= 10) sink.complete();
                            return counterState + 1;
                        }
                )
                .subscribe(ScratchUtil.subscriber());

    }

}
