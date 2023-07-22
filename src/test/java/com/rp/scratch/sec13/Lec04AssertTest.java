package com.rp.scratch.sec13;

import com.rp.scratch.course.sec09.helper.bookstore.Book;
import com.rp.scratch.course.sec09.helper.bookstore.BookOrder;
import com.rp.scratch.util.ScratchUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Supplier;

public class Lec04AssertTest {

    @Test
    public void test1() {

        // Assemble
        Mono<BookOrder> testInput = this.getBookOrderMono();

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .assertNext(order -> Assertions.assertNotNull(order.book().name()))
                .verifyComplete();

    }

    @Test
    public void test2() {

        // Assemble
        Mono<BookOrder> testInput = this.getBookOrderMono()
                .delayElement(Duration.ofSeconds(3));

        // Act (Subscribe) + Assert
        StepVerifier.create(testInput)
                .assertNext(order -> Assertions.assertNotNull(order.book().name()))
                .expectComplete()
                .verify(Duration.ofSeconds(5)); // can be used to verify a process takes a maximum amount of time

    }

    private Mono<BookOrder> getBookOrderMono() {
        Supplier<Book> bookCreate = () -> new Book(ScratchUtil.faker().book().title(), ScratchUtil.faker().book().genre());
        Supplier<BookOrder> orderCreate = () -> new BookOrder(bookCreate.get(), 50);
        return Mono.fromSupplier(orderCreate);
    }

}
