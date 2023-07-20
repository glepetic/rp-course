package com.rp.scratch.course.sec09.helper.bookstore;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Accountant {

    private static Accountant instance = null;

    private final Map<String, Double> revenue;

    private final Bookstore bookstore;

    private Accountant(Bookstore bookstore) {
        this.revenue = IntStream.range(0, 3)
                .mapToObj(i -> ScratchUtil.faker().book().genre())
                .collect(Collectors.toMap(genre -> genre, genre -> 0d));
        this.bookstore = bookstore;
    }

    public static Accountant instance() {
        return instance = Objects.isNull(instance) ? new Accountant(Bookstore.instance()) : instance;
    }

    public Flux<RevenueReport> getRevenueReport() {
        return this.bookstore.orderStream()
                .filter(order -> this.revenue.containsKey(order.book().genre()))
                .bufferTimeout(5, Duration.ofSeconds(5))
                .map(this::getRevenueReport);
    }

    private RevenueReport getRevenueReport(List<BookOrder> orders) {
        orders.forEach(this::updateRevenue);
        return new RevenueReport(LocalDateTime.now(), this.revenue);
    }

    private void updateRevenue(BookOrder order) {
        String genre = order.book().genre();
        this.revenue.put(genre, this.revenue.getOrDefault(genre, 0d) + order.price());
    }

}
