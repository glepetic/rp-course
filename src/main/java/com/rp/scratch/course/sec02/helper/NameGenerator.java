package com.rp.scratch.course.sec02.helper;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class NameGenerator {

    public static Flux<String> getNamesReactive(int count) {
        return Flux.range(0, count)
                .map(i -> getName());
    }

    public static List<String> getNames(int count) {
        return IntStream.range(0, count)
                .boxed()
                .map(i -> getName())
                .collect(Collectors.toList());
    }

    private static String getName() {
        ScratchUtil.sleepSeconds(1);
        return ScratchUtil.faker().name().fullName();
    }

}
