package com.rp.scratch.course.sec08.assignment;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CarDemandService {

    private static CarDemandService instance = null;

    private final Map<Brand, Flux<Double>> demandFactors;

    private final CalendarService calendarService;

    private CarDemandService(CalendarService calendarService) {
        this.calendarService = calendarService;
        this.demandFactors = Arrays.stream(Brand.values())
                .collect(Collectors.toMap(Function.identity(), this::getDemandFactor));
    }

    public static CarDemandService instance() {
        return instance = Objects.isNull(instance) ? new CarDemandService(CalendarService.instance()) : instance;
    }

    public Flux<Double> demandFactor(Brand brand) {
        return this.demandFactors.get(brand);
    }

    private Flux<Double> getDemandFactor(Brand brand) {
        return this.calendarService.monthStream()
                .filter(month -> month % 3 == 0)
                .map(month -> this.calculateFactor(brand))
                .startWith((brand.getMaximumFactor() + brand.getMinimumFactor()) / 2)
                .publish()
                .autoConnect(0);
    }

    private Double calculateFactor(Brand brand) {
        double randomFactor = ScratchUtil.faker().random().nextDouble();
        return brand.getMinimumFactor() + randomFactor * (brand.getMaximumFactor() - brand.getMinimumFactor());
    }

}
