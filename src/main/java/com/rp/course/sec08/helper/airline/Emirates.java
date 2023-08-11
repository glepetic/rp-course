package com.rp.course.sec08.helper.airline;

import com.rp.util.Util;

import java.util.Objects;

public class Emirates extends Airline {

    private static Emirates instance = null;

    private Emirates() {
        super("Emirates", () -> Util.faker().random().nextInt(1, 10));
    }

    public static Emirates instance() {
        return instance = Objects.isNull(instance) ? new Emirates() : instance;
    }

}
