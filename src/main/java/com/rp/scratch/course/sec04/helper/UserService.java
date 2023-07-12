package com.rp.scratch.course.sec04.helper;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

public class UserService {

    private static UserService instance = null;

    private final List<User> users;

    private UserService() {
        this.users = List.of(
                User.create(1),
                User.create(2),
                User.create(3),
                User.create(4),
                User.create(5)
        );
    }

    public static UserService instance() {
        return instance = Objects.isNull(instance) ? new UserService() : instance;
    }

    public Flux<User> getUsers() {
        return Flux.fromIterable(this.users);
    }

}
