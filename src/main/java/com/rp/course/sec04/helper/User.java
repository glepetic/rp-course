package com.rp.course.sec04.helper;

import com.rp.util.Util;

public record User(int userId, String name) {

    public static User create(int userId) {
        return new User(userId, Util.faker().name().firstName());
    }

}
