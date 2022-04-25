package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.flights.AddFlightRequest;
import io.codelex.flightplanner.flights.Flight;
import io.codelex.flightplanner.flights.FlightsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping ("/admin-api")
public class AdminClientController {
    private final FlightsService flightsService;

    public AdminClientController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@Valid @NotNull @RequestBody AddFlightRequest addFlightRequest) {
        return flightsService.addFlight(addFlightRequest);
    }

    @GetMapping("/flights/{id}")
    public synchronized Flight fetchFlight(@PathVariable("id") Long id) {
        return flightsService.getFlightById(id);
    }

    @DeleteMapping("/flights/{id}")
    public synchronized void deleteFlight(@NotNull @PathVariable("id") Long id) {
        flightsService.deleteFlight(id);
    }

}