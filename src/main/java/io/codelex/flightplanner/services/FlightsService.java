package io.codelex.flightplanner.services;

import io.codelex.flightplanner.classes.AddFlightRequest;
import io.codelex.flightplanner.classes.Flight;
import io.codelex.flightplanner.classes.PageResult;
import io.codelex.flightplanner.classes.SearchFlightsRequest;

public interface FlightsService {

    Flight addFlight(AddFlightRequest addFlightRequest);

    Flight getFlightById(Long id);

    void deleteFlight(Long id);

    void clearFlights();

    PageResult searchFlights(SearchFlightsRequest searchFlightsRequest);
}
