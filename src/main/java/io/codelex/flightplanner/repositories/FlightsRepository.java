package io.codelex.flightplanner.repositories;

import io.codelex.flightplanner.classes.AddFlightRequest;
import io.codelex.flightplanner.classes.Flight;
import io.codelex.flightplanner.classes.PageResult;
import io.codelex.flightplanner.classes.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class FlightsRepository {
    private final AirportRepository airportRepository;
    private final List<Flight> listOfFlights = new ArrayList<>();
    private Long id = 0L;

    public FlightsRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public synchronized Flight addFlight(AddFlightRequest addFlightRequest) {
        for (Flight flight : listOfFlights) {
            if (flight.isEqualFlight(addFlightRequest)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }

        if (addFlightRequest.airportsAreEqual()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!addFlightRequest.datesAreCorrect()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        id++;
        airportRepository.addAirport(addFlightRequest.getFrom());
        airportRepository.addAirport(addFlightRequest.getTo());

        Flight newFlight = new Flight(id, addFlightRequest);
        listOfFlights.add(newFlight);
        return newFlight;
    }

    public synchronized Flight getFlightById(Long id) {
        return listOfFlights
                .stream()
                .filter(flight -> Objects.equals(flight.getId(), id))
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PageResult searchFlights(SearchFlightsRequest searchFlightsRequest) {
        return new PageResult(listOfFlights
                .stream()
                .filter(flight -> flight.matchesSearchRequest(searchFlightsRequest))
                .collect(Collectors.toList()));
    }

    public synchronized void deleteFlight(Long id) {
        listOfFlights.removeIf(flight -> flight.getId() == id);
    }

    public void clearFlights() {
        listOfFlights.clear();
        airportRepository.clearAirportList();
    }
}
