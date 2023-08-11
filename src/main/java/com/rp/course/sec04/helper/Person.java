package com.rp.course.sec04.helper;

import com.rp.util.Util;

public record Person(String name, int age) {
    public static Person create() {
        return new Person(Util.faker().name().firstName(), Util.faker().random().nextInt(1, 30));
    }

}
