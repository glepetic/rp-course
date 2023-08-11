package com.rp.course.sec08.assignment;

import com.rp.course.sec08.assignment.model.Brand;
import com.rp.course.sec08.assignment.model.Car;
import com.rp.course.sec08.assignment.service.CarValueService;
import com.rp.util.Util;

public class CarPriceDemo {

    private static final CarValueService carValueService = CarValueService.instance();

    public static void main(String[] args) {

        String priceString = Util.faker().commerce().price(5000, 10000);
        int price = Math.toIntExact(Math.round(Double.parseDouble(priceString)));
        Car car = new Car(price, Brand.TOYOTA);

        System.out.println("Purchase price: " + car.price());

        carValueService.getRealTimeValue(car)
                // added to get completion signal eventually
                .takeUntil(value -> value <= car.price() * 0.5)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

}
