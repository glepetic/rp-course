package com.rp.course.sec09.assignment.processor;

import com.rp.course.sec09.assignment.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
                .flatMap(order -> Flux.concat(Mono.just(order), this.getFreeItem(order)));
    }

    private Mono<Order> getFreeItem(Order order) {
        return Mono.fromSupplier(() -> new Order("FREE " + order.item(), order.category(), 0));
    }

}
