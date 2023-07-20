package com.rp.scratch.course.sec09.assignment.processor;

import com.rp.scratch.course.sec09.assignment.model.Order;
import reactor.core.publisher.Flux;

public interface OrderProcessor {
    Flux<Order> process(Flux<Order> orders);
}
