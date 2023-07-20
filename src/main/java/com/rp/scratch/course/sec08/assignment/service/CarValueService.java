package com.rp.scratch.course.sec08.assignment.service;

import com.rp.scratch.course.sec08.assignment.model.Car;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class CarValueService {

    private static CarValueService instance = null;

    private final CarDemandService carDemandService;
    private final CarDepreciationService carDepreciationService;

    private CarValueService(CarDemandService carDemandService,
                            CarDepreciationService carDepreciationService) {
        this.carDemandService = carDemandService;
        this.carDepreciationService = carDepreciationService;
    }

    public static CarValueService instance() {
        return instance = Objects.isNull(instance) ?
                new CarValueService(CarDemandService.instance(), CarDepreciationService.instance()) :
                instance;
    }

    public Flux<Long> getRealTimeValue(Car car) {
        return Flux.combineLatest(
                        this.carDemandService.demandFactor(car.brand()).startWith(1d),
                        this.carDepreciationService.getDepreciation(car.brand()),
                        (demandFactor, depreciation) -> this.calculateRealTimeValue(demandFactor, depreciation, car.price())
                );
    }

    private Long calculateRealTimeValue(Double demandFactor, Integer depreciation, Integer carPrice) {
        return Math.round(Math.max(carPrice * 0.1, demandFactor * (carPrice - depreciation)));
    }

}
