package com.rp.course.sec02.assignment;

import java.util.concurrent.CountDownLatch;

public class StockPriceDemo {

    private final static StockPriceService stockPriceService = StockPriceService.instance();

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        stockPriceService.getStockPrice()
                .subscribeWith(InvestorSubscriber.fromLatch(latch));

        latch.await();

    }

}
