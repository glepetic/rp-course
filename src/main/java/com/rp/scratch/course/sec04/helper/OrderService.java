package com.rp.scratch.course.sec04.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderService {

    private static OrderService instance = null;

    private final Map<Integer, List<PurchaseOrder>> orders;

    private OrderService() {
        this.orders = Map.of(
                1,
                List.of(
                        PurchaseOrder.create(1),
                        PurchaseOrder.create(1),
                        PurchaseOrder.create(1)
                ),
                2,
                List.of(PurchaseOrder.create(2),
                        PurchaseOrder.create(2))
        );
    }

    public static OrderService instance() {
        return instance = Objects.isNull(instance) ? new OrderService() : instance;
    }

    public Flux<PurchaseOrder> getOrders(int userId) {
        return Flux.fromIterable(this.orders.getOrDefault(userId, List.of()))
                .delayElements(Duration.ofSeconds(1));
    }

}
