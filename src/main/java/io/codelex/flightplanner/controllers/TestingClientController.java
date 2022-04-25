package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.flights.FlightsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testing-api")
public class TestingClientController {
    private final FlightsService flightPlannerService;

    public TestingClientController(FlightsService flightPlannerService) {
        this.flightPlannerService = flightPlannerService;
    }

    @PostMapping("/clear")
    public void clearFlights() {
        flightPlannerService.clearFlights();
    }
}
