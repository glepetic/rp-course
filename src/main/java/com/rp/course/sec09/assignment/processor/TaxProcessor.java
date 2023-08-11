package com.rp.course.sec09.assignment.processor;

import com.rp.course.sec09.assignment.model.Order;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class TaxProcessor implements OrderProcessor {

    private static OrderProcessor instance = null;

    // percentage tax
    private final Double tax = 10d;

    private TaxProcessor() {
    }

    public static OrderProcessor instance() {
        return instance = Objects.isNull(instance) ? new TaxProcessor() : instance;
    }

    @Override
    public Flux<Order> process(Flux<Order> orders) {
        return orders.doOnNext(order -> System.out.println("Packaging: " + order))
                .map(order -> new Order(this.pack(order.item()), order.category(), order.price() * (1 + (tax/ 100))));
    }

    private String pack(String item) {
        return "{{ " + item + " }}";
    }

}
