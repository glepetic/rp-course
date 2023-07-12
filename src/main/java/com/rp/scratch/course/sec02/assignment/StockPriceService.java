package com.rp.scratch.course.sec02.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;

public class StockPriceService {

    private static StockPriceService instance = null;
    private final Random fluctuator;
    private Integer stockPrice;

    private StockPriceService() {
        fluctuator = new Random();
        stockPrice = 100;
    }

    public static StockPriceService instance() {
        return instance = Objects.isNull(instance) ? new StockPriceService() : instance;
    }

    public Flux<Integer> getStockPrice() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> this.updateStockPrice());
    }

    private Integer updateStockPrice() {
        return stockPrice = stockPrice + fluctuator.nextInt(-5, 5);
    }

}
