package com.rp.scratch.course.sec08.helper.airline;

import com.rp.scratch.util.ScratchUtil;

import java.util.Objects;

public class QatarAirways extends Airline {

    private static QatarAirways instance = null;

    private QatarAirways() {
        super("Qatar", () -> ScratchUtil.faker().random().nextInt(1, 5));
    }

    public static QatarAirways instance() {
        return instance = Objects.isNull(instance) ? new QatarAirways() : instance;
    }

}
