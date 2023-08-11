package com.rp.course.sec08.helper.airline;

import com.rp.util.Util;

import java.util.Objects;

public class AmericanAirlines extends Airline {

    private static AmericanAirlines instance = null;

    private AmericanAirlines() {
        super("AA", () -> Util.faker().random().nextInt(1, 10));
    }

    public static AmericanAirlines instance() {
        return instance = Objects.isNull(instance) ? new AmericanAirlines() : instance;
    }

}
