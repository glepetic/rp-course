package com.rp.course.sec02.assignment;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

public class StockPriceDemo {

    private final static StockPriceService stockPriceService = StockPriceService.getInstance();

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        stockPriceService.getStockPrice()
                .subscribeWith(InvestorSubscriber.fromLatch(latch));

        latch.await();

    }

}
