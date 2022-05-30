package io.codelex.flightplanner.repositories;

import io.codelex.flightplanner.classes.AddFlightRequest;
import io.codelex.flightplanner.classes.Flight;
import io.codelex.flightplanner.classes.PageResult;
import io.codelex.flightplanner.classes.SearchFlightsRequest;
import io.codelex.flightplanner.services.AbstractFlightService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class FlightsRepository extends AbstractFlightService {
    private final AirportRepository airportRepository;
    private final List<Flight> listOfFlights = new ArrayList<>();
    private Long id = 0L;

    public FlightsRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public void dataVerification(AddFlightRequest addFlightRequest) {
        super.dataVerification(addFlightRequest);
    }

    public synchronized Flight addFlight(AddFlightRequest addFlightRequest) {
        for (Flight flights : listOfFlights) {
            if (flights.areEqualFlights(addFlightRequest)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }
        dataVerification(addFlightRequest);

        id++;
        airportRepository.addAirport(addFlightRequest.getFrom());
        airportRepository.addAirport(addFlightRequest.getTo());

        Flight newFlight = new Flight(addFlightRequest);
        newFlight.setId(id);
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
        listOfFlights.removeIf(flight -> flight.getId().equals(id));
    }

    public void clearFlights() {
        listOfFlights.clear();
        airportRepository.clearAirportList();
    }
}
