package com.rp.scratch.course.sec08.helper.airline;

import com.rp.scratch.util.ScratchUtil;

import java.util.Objects;

public class AmericanAirlines extends Airline {

    private static AmericanAirlines instance = null;

    private AmericanAirlines() {
        super("AA", () -> ScratchUtil.faker().random().nextInt(1, 10));
    }

    public static AmericanAirlines instance() {
        return instance = Objects.isNull(instance) ? new AmericanAirlines() : instance;
    }

}
