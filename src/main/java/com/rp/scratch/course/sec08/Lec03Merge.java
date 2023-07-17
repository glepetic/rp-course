package com.rp.scratch.course.sec08;

import com.rp.scratch.course.sec08.helper.airline.Airline;
import com.rp.scratch.course.sec08.helper.airline.AmericanAirlines;
import com.rp.scratch.course.sec08.helper.airline.Emirates;
import com.rp.scratch.course.sec08.helper.airline.QatarAirways;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec03Merge {

    public static void main(String[] args) {

        Airline qatar = QatarAirways.instance();
        Airline emirates = Emirates.instance();
        Airline american = AmericanAirlines.instance();

        Flux<String> flights = Flux.merge(
                qatar.getFlights(),
                emirates.getFlights(),
                american.getFlights()
        );

        flights.subscribe(ScratchUtil.subscriber());

        ScratchUtil.sleepSeconds(10);

    }

}
