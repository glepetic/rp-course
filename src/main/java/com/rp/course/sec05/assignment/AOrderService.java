package com.rp.course.sec05.assignment;

import com.rp.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class AOrderService {

    private static AOrderService instance = null;
    private final Flux<APurchaseOrder> flux;

    private AOrderService() {
        this.flux = this.startStream();
    }

    public static AOrderService instance() {
        return instance = Objects.isNull(instance) ? new AOrderService() : instance;
    }

    private final Consumer<SynchronousSink<APurchaseOrder>> orderEmitter = sink -> sink.next(this.getNext());

    public Flux<APurchaseOrder> orderStream() {
        return this.flux;
    }

    private Flux<APurchaseOrder> startStream() {
        return Flux.generate(orderEmitter)
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(0);
    }

    private APurchaseOrder getNext() {
        List<String> companyProducts = AppProperties.instance().companyProducts();
        int productIndex = Util.faker().random().nextInt(0, companyProducts.size() - 1);
        return APurchaseOrder.create(companyProducts.get(productIndex));
    }

}
