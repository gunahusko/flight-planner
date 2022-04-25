package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.flights.Airport;
import io.codelex.flightplanner.flights.Flight;
import io.codelex.flightplanner.flights.FlightsService;
import io.codelex.flightplanner.search.PageResult;
import io.codelex.flightplanner.search.SearchFlightsRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerClientController {
    private final FlightsService flightsService;

    public CustomerClientController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @GetMapping("/airports")
    public synchronized List<Airport> searchAirports(@NotNull @PathParam(value = "search") String search) {
        return flightsService.getAirportList(search);
    }

    @PostMapping("/flights/search")
    public synchronized PageResult searchFlights(@Valid @NotNull @RequestBody SearchFlightsRequest searchFlightsRequest) {
        return flightsService.searchFlights(searchFlightsRequest);
    }

    @GetMapping("/flights/{id}")
    public synchronized Flight findFlightById(@NotNull @PathVariable("id") Long id) {
        return flightsService.getFlightById(id);
    }

}
