package com.rp.course.sec04;

import com.rp.course.sec04.helper.Person;
import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.UnaryOperator;

public class Lec11SwitchOnFirst {

    public static void main(String[] args) {

        getPerson()
                // used to make decisions based on first item
                .switchOnFirst((signal, flux) -> Mono.justOrEmpty(signal.get())
                        .filter(p -> p.age() > 10)
                        .flatMapMany(p -> flux)
                        .switchIfEmpty(Flux.defer(() -> applyFilter().apply(flux))))
                .subscribe(Util.subscriber());

    }

    private static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> Person.create());
    }

    private static UnaryOperator<Flux<Person>> applyFilter() {
        return flux -> flux.filter(p -> p.age() > 10)
                .doOnDiscard(Person.class, p -> System.out.println("Discarded: " + p));
    }

}
