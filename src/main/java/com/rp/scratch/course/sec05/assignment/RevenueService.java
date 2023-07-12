package com.rp.scratch.course.sec05.assignment;

import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RevenueService {

    private static RevenueService instance = null;
    private final Map<String, Double> revenue;

    private final AOrderService orderService;

    private RevenueService(AOrderService orderService) {
        this.revenue = AppProperties.instance()
                .companyProducts()
                .stream()
                .collect(Collectors.toMap(Function.identity(), item -> 0d));
        this.orderService = orderService;
    }

    public static RevenueService instance() {
        return instance = Objects.isNull(instance) ? new RevenueService(AOrderService.instance()) : instance;
    }

    public Flux<Map<String, Double>> revenueStream() {
        return this.orderService.orderStream()
                .map(this::updateRevenue);
    }

    private Map<String, Double> updateRevenue(APurchaseOrder order) {
        return Optional.ofNullable(this.revenue.get(order.item()))
                .map(current -> current + order.price())
                .map(updated -> this.updateRevenue(order.item(), updated))
                .orElseThrow(() -> new RuntimeException("Invalid order: " + order));
    }

    private Map<String, Double> updateRevenue(String product, Double value) {
        this.revenue.put(product, value);
        return this.revenue;
    }


}
