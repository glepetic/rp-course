package com.rp.course.sec08.assignment.service;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Month;
import java.util.Objects;

public class CalendarService {

    private static CalendarService instance = null;

    private final Flux<Integer> monthsFlux;

    private CalendarService() {
        this.monthsFlux = this.getMonths();
    }

    public static CalendarService instance() {
        return instance = Objects.isNull(instance) ? new CalendarService() : instance;
    }

    public Flux<Integer> monthStream() {
        return this.monthsFlux;
    }

    private Flux<Integer> getMonths() {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1))
                .map(this::toMonth)
                .doOnNext(month -> System.out.println("Current month: " + Month.of(month)))
                .publish()
                .autoConnect(0);
    }

    private Integer toMonth(Long currentIteration) {
        int plusOne = currentIteration.intValue() + 1;
        int month = plusOne % 12;
        return month == 0 ? 12 : month;
    }

}
