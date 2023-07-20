package com.rp.scratch.course.sec09.assignment.service;

import com.rp.scratch.course.sec09.assignment.model.Order;
import com.rp.scratch.course.sec09.assignment.model.ProductCategory;
import com.rp.scratch.course.sec09.assignment.processor.DiscountProcessor;
import com.rp.scratch.course.sec09.assignment.processor.OrderProcessor;
import com.rp.scratch.course.sec09.assignment.processor.TaxProcessor;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class OrderConsumer {

    private static OrderConsumer instance = null;

    private final Map<ProductCategory, OrderProcessor> processors;

    private final OrderProducer orderProducer;

    private OrderConsumer(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
        this.processors = Map.of(
                ProductCategory.KIDS, DiscountProcessor.instance(),
                ProductCategory.AUTOMOTIVE, TaxProcessor.instance()
        );
    }

    public static OrderConsumer instance() {
        return instance = Objects.isNull(instance) ? new OrderConsumer(OrderProducer.instance()) : instance;
    }

    public Flux<Order> processOrders() {
        return this.orderProducer.orderStream()
                .filter(order -> this.processors.keySet()
                        .stream()
                        .anyMatch(productCategory -> order.category()
                                .toUpperCase()
                                .contains(productCategory.name().toUpperCase())))
                .doOnDiscard(Order.class, order -> System.out.println("Discarded: " + order.category()))
                .groupBy(order -> this.getKey(order.category()))
                .flatMap(groupedFlux -> this.getProcessor(groupedFlux.key()).process(groupedFlux));
    }

    private ProductCategory getKey(String category) {
        return Optional.ofNullable(category)
                .map(String::toUpperCase)
                .flatMap(cat -> Optional.of(cat)
                        .filter(c -> c.contains(ProductCategory.KIDS.name()))
                        .map(c -> ProductCategory.KIDS)
                        .or(() -> Optional.of(cat)
                                .filter(c -> c.contains(ProductCategory.AUTOMOTIVE.name()))
                                .map(c -> ProductCategory.AUTOMOTIVE)))
                .orElseThrow(() -> new RuntimeException("Unknown processing for category: " + category));

    }

    private OrderProcessor getProcessor(ProductCategory category) {
        return Optional.ofNullable(category)
                .filter(cat -> cat.equals(ProductCategory.KIDS))
                .map(cat -> DiscountProcessor.instance())
                .or(() -> Optional.ofNullable(category)
                        .filter(c -> c.equals(ProductCategory.AUTOMOTIVE))
                        .map(c -> TaxProcessor.instance()))
                .orElseThrow(() -> new RuntimeException("Invalid category. No processor found for " + category));
    }

}
