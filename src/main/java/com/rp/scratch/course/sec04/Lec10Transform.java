package com.rp.scratch.course.sec04;

import com.rp.scratch.course.sec04.helper.Person;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec10Transform {

    public static void main(String[] args) {

        getPerson()
                .transform(applyFilterMap())
                .subscribe(ScratchUtil.subscriber());

    }

    private static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> Person.create());
    }

    private static Function<Flux<Person>, Flux<String>> applyFilterMap() {
        return flux -> flux.filter(p -> p.age() > 10)
                .map(Person::name)
                .doOnDiscard(Person.class, p -> System.out.println("Discarded: " + p));
    }

}
