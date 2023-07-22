package com.rp.scratch.course.sec12.helper.rateLimit;

import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class BookService {

    private static BookService instance = null;

    private final Map<UserCategory, Integer> rateLimits;

    private BookService() {
        this.rateLimits = new HashMap<>();
        this.rateLimits.put(UserCategory.STANDARD, 2);
        this.rateLimits.put(UserCategory.PREMIUM, 3);
    }

    public static BookService instance() {
        return instance = Objects.isNull(instance) ? new BookService() : instance;
    }

    public Mono<String> getBook() {
        return Mono.deferContextual(ctx -> Mono.just(ctx)
                        .filter(context -> context.get("allow"))
                        .map(context -> ScratchUtil.faker().book().title()))
                .switchIfEmpty(Mono.error(() -> new RuntimeException("--not allowed--")))
                .contextWrite(this.rateLimiterContext());
    }

    private UnaryOperator<Context> rateLimiterContext() {
        return context -> {
            if (context.hasKey("category")) {
                UserCategory category = context.get("category");
                Integer attempts = this.rateLimits.get(category);
                if (attempts > 0) {
                    this.rateLimits.put(category, attempts - 1);
                    return context.put("allow", true);
                }
            }
            return context.put("allow", false);
        };
    }

}
