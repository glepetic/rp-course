package com.rp.scratch.course.sec09.assignment.processor;

import com.rp.scratch.course.sec09.assignment.model.Order;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class DiscountProcessor implements OrderProcessor {

    private static OrderProcessor instance = null;

    private DiscountProcessor() {
    }

    public static OrderProcessor instance() {
        return instance = Objects.isNull(instance) ? new DiscountProcessor() : instance;
    }

    @Override
    public Flux<Order> process(Flux<Order> orders) {
        return orders.doOnNext(order -> System.out.println("Applying 2x1 promotion to: " + order))
                .flatMap(order -> Flux.just(order, new Order("FREE " + order.item(), order.category(), 0)));
    }

}
