package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.classes.Airport;
import io.codelex.flightplanner.classes.Flight;
import io.codelex.flightplanner.services.FlightsService;
import io.codelex.flightplanner.classes.PageResult;
import io.codelex.flightplanner.classes.SearchFlightsRequest;
import io.codelex.flightplanner.services.AirportService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerClientController {
    private final FlightsService flightsService;
    private final AirportService airportService;

    public CustomerClientController(FlightsService flightsService, AirportService airportService) {
        this.flightsService = flightsService;
        this.airportService = airportService;
    }

    @GetMapping("/airports")
    public List<Airport> searchAirports(@PathParam("search") String search) {
        return airportService.searchAirports(search);
    }

    @PostMapping("/flights/search")
    public PageResult searchFlights(@Valid @RequestBody SearchFlightsRequest searchFlightsRequest) {
        return flightsService.searchFlights(searchFlightsRequest);
    }

    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable Long id) {
        return flightsService.getFlightById(id);
    }
}
