package com.rp.course.sec05.assignment;

import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InventoryService {

    private static final Integer INITIAL_STOCK = 1000;

    private static InventoryService instance = null;
    private final Map<String, Integer> stock;

    private final AOrderService orderService;

    private InventoryService(AOrderService orderService) {
        this.stock = AppProperties.instance()
                .companyProducts()
                .stream()
                .collect(Collectors.toMap(Function.identity(), item -> INITIAL_STOCK));
        this.orderService = orderService;
    }

    public static InventoryService instance() {
        return instance = Objects.isNull(instance) ? new InventoryService(AOrderService.instance()) : instance;
    }

    public Flux<Map<String, Integer>> inventoryStream() {
        return this.orderService.orderStream()
                .map(this::updateStock);
    }

    private Map<String, Integer> updateStock(APurchaseOrder order) {
        return Optional.ofNullable(this.stock.get(order.item()))
                .map(current -> current - order.quantity())
                .map(updated -> this.updateStock(order.item(), updated))
                .orElseThrow(() -> new RuntimeException("Invalid order: " + order));
    }

    private Map<String, Integer> updateStock(String product, Integer value) {
        this.stock.put(product, value);
        return this.stock;
    }


}
