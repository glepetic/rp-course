package com.rp.course.sec05.assignment;

import com.rp.util.Util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppProperties {

    private static AppProperties instance = null;

    private final List<String> companyProducts;

    private AppProperties() {
        this.companyProducts = IntStream.range(0, 2)
                .mapToObj(i -> Util.faker().commerce().productName())
                .collect(Collectors.toSet())
                .stream()
                .toList();
    }

    public static AppProperties instance() {
        return instance = Objects.isNull(instance) ? new AppProperties() : instance;
    }

    public List<String> companyProducts() {
        return companyProducts;
    }
}
