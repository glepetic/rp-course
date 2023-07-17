package com.rp.scratch.course.sec08.assignment;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarDepreciationService {

    private static CarDepreciationService instance = null;

    private final Map<Brand, Integer> depreciationRate;

    private final CalendarService calendarService;

    private CarDepreciationService(CalendarService calendarService) {
        this.calendarService = calendarService;
        this.depreciationRate = Arrays.stream(Brand.values())
                .collect(Collectors.toMap(Function.identity(), brand -> ScratchUtil.faker().random().nextInt(90, 120)));
    }

    public static CarDepreciationService instance() {
        return instance = Objects.isNull(instance) ? new CarDepreciationService(CalendarService.instance()) : instance;
    }

    public Flux<Integer> getDepreciation(Brand brand) {
        AtomicInteger accumDepreciation = new AtomicInteger(0);
        return this.calendarService.monthStream()
                .map(month -> accumDepreciation.addAndGet(this.depreciationRate.get(brand)));
    }

}
