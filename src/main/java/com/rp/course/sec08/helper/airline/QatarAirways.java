package com.rp.course.sec08.helper.airline;

import com.rp.util.Util;

import java.util.Objects;

public class QatarAirways extends Airline {

    private static QatarAirways instance = null;

    private QatarAirways() {
        super("Qatar", () -> Util.faker().random().nextInt(1, 5));
    }

    public static QatarAirways instance() {
        return instance = Objects.isNull(instance) ? new QatarAirways() : instance;
    }

}
