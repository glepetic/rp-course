package com.rp.scratch.course.sec04.helper;

import com.rp.scratch.util.ScratchUtil;

public record User(int userId, String name) {

    public static User create(int userId) {
        return new User(userId, ScratchUtil.faker().name().firstName());
    }

}
