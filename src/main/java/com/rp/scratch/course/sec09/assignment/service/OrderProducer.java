package com.rp.scratch.course.sec09.assignment.service;

import com.rp.scratch.course.sec09.assignment.model.Order;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class OrderProducer {

    private static OrderProducer instance = null;

    private OrderProducer() {}

    public static OrderProducer instance() {
        return instance = Objects.isNull(instance) ? new OrderProducer() : instance;
    }

    public Flux<Order> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> this.create())
                .doOnNext(order -> System.out.println("Emitting: " + order));
    }

    private Order create() {
        String item = ScratchUtil.faker().commerce().productName();
        String category = ScratchUtil.faker().commerce().department();
        double price = Double.parseDouble(ScratchUtil.faker().commerce().price());
        return new Order(item, category, price);
    }

}
