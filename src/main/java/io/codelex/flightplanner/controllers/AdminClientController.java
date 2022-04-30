package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.classes.AddFlightRequest;
import io.codelex.flightplanner.classes.Flight;
import io.codelex.flightplanner.services.FlightsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api/flights")
public class AdminClientController {
    private final FlightsService flightsService;

    public AdminClientController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flight addFlight(@Valid @RequestBody AddFlightRequest addFlightRequest) {
        return flightsService.addFlight(addFlightRequest);
    }

    @GetMapping("/{id}")
    public Flight fetchFlight(@PathVariable Long id) {
        return flightsService.getFlightById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightsService.deleteFlight(id);
    }

}