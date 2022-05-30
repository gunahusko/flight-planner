package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.services.FlightsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestingClientController {
    private final FlightsService flightsService;

    public TestingClientController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @PostMapping("/clear")
    public void clearFlights() {
        flightsService.clearFlights();
    }

}
