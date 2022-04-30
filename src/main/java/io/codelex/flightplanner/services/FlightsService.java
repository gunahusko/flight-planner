package io.codelex.flightplanner.services;

import io.codelex.flightplanner.classes.AddFlightRequest;
import io.codelex.flightplanner.classes.Flight;
import io.codelex.flightplanner.repositories.FlightsRepository;
import io.codelex.flightplanner.classes.PageResult;
import io.codelex.flightplanner.classes.SearchFlightsRequest;
import org.springframework.stereotype.Service;

@Service
public class FlightsService {
    private final FlightsRepository flightPlannerRepository;

    public FlightsService(FlightsRepository flightPlannerRepository) {
        this.flightPlannerRepository = flightPlannerRepository;
    }

    public Flight addFlight(AddFlightRequest addFlightRequest) {
        return flightPlannerRepository.addFlight(addFlightRequest);
    }

    public Flight getFlightById(Long id) {
        return flightPlannerRepository.getFlightById(id);
    }

    public void deleteFlight(Long id) {
        flightPlannerRepository.deleteFlight(id);
    }

    public void clearFlights() {
        flightPlannerRepository.clearFlights();
    }

    public PageResult searchFlights(SearchFlightsRequest searchFlightsRequest) {
        return flightPlannerRepository.searchFlights(searchFlightsRequest);
    }
}
