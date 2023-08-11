package com.rp.course.sec08;

import com.rp.course.sec08.helper.airline.Airline;
import com.rp.course.sec08.helper.airline.AmericanAirlines;
import com.rp.course.sec08.helper.airline.Emirates;
import com.rp.course.sec08.helper.airline.QatarAirways;
import com.rp.util.Util;
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

        flights.subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }

}
