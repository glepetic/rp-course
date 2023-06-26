package com.rp.course.sec02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromArrayOrList {

    public static void main(String[] args) {

        String[] myArr = new String[]{"hello", "world", "!"};
        Flux<String> fluxFromArray = Flux.fromArray(myArr);

        fluxFromArray.subscribe(Util.onNextPrintValue());

        List<String> myList = List.of("bye", "universe", "?");
        Flux<String> fluxFromList = Flux.fromIterable(myList);

        fluxFromList.subscribe(Util.onNextPrintValue());

    }

}
