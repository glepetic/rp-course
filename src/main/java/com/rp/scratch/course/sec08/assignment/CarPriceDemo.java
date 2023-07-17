package com.rp.scratch.course.sec08.assignment;

import com.rp.scratch.course.sec08.assignment.model.Brand;
import com.rp.scratch.course.sec08.assignment.model.Car;
import com.rp.scratch.course.sec08.assignment.service.CarValueService;
import com.rp.scratch.util.ScratchUtil;

public class CarPriceDemo {

    private static final CarValueService carValueService = CarValueService.instance();

    public static void main(String[] args) {

        String priceString = ScratchUtil.faker().commerce().price(5000, 10000);
        Long price = Math.round(Double.parseDouble(priceString));
        Car car = new Car(price.intValue(), Brand.TOYOTA);

        System.out.println("Purchase price: " + car.price());

        carValueService.getRealTimeValue(car)
                // added to get completion signal eventually
                .takeUntil(value -> value <= car.price() * 0.5)
                .subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(60);

    }

}
