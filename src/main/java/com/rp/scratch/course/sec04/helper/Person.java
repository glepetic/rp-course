package com.rp.scratch.course.sec04.helper;

import com.rp.scratch.util.ScratchUtil;

public record Person(String name, int age) {
    public static Person create() {
        return new Person(ScratchUtil.faker().name().firstName(), ScratchUtil.faker().random().nextInt(1, 30));
    }

}
