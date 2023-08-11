package com.rp.course.sec09.helper.bookstore;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Objects;

public class Bookstore {

    private static Bookstore instance = null;

    private Bookstore() {}

    public static Bookstore instance() {
        return instance = Objects.isNull(instance) ? new Bookstore() : instance;
    }

    public Flux<BookOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> this.create());
    }

    private BookOrder create() {
        Book book = new Book(Util.faker().book().title(), Util.faker().book().genre());
        double price = BigDecimal.valueOf(Double.parseDouble(Util.faker().commerce().price(10, 30)))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        return new BookOrder(book, price);
    }

}
