package com.rp.scratch.course.sec12.helper.rateLimit;

import reactor.util.context.Context;

import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class UserCategoryService {

    private static UserCategoryService instance = null;

    private final Map<String, UserCategory> users;

    private UserCategoryService() {
        this.users = Map.of(
                "sam", UserCategory.STANDARD,
                "paul", UserCategory.PREMIUM
        );
    }

    public static UserCategoryService instance() {
        return instance = Objects.isNull(instance) ? new UserCategoryService() : instance;
    }

    public UnaryOperator<Context> context() {
        // assuming user is always loaded in context
        return context -> context.put("category", this.users.get(context.get("user").toString()));
    }
}
