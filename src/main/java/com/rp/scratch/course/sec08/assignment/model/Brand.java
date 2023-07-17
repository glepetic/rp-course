package com.rp.scratch.course.sec08.assignment.model;

import lombok.Getter;

public enum Brand {
    TOYOTA(0.8, 1.2),
    FORD(0.6, 1d),
    AUDI(0.9, 1.15),
    FERRARI(0.9, 1.1),
    VOLKSWAGEN(0.5, 1.5),

    ;

    private final @Getter Double minimumFactor;
    private final @Getter Double maximumFactor;

    Brand(Double minimumFactor, Double maximumFactor) {
        this.minimumFactor = minimumFactor;
        this.maximumFactor = maximumFactor;
    }

}
