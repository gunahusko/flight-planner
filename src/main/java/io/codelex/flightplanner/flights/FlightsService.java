package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.search.PageResult;
import io.codelex.flightplanner.search.SearchFlightsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Airport> getAirportList(String search) {
        return flightPlannerRepository.getAirportList(search);
    }
}
