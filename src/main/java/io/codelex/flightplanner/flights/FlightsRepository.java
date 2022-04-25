package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.search.PageResult;
import io.codelex.flightplanner.search.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightsRepository {
    private final List<Flight> listOfFlights = new ArrayList<>();
    private final List<Airport> airportList = new ArrayList<>();
    private Long id = 0L;

    public synchronized Flight addFlight(AddFlightRequest addFlightRequest) {
        for (Flight flight : listOfFlights) {
            if (flight.matches(addFlightRequest)) {
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
        airportList.add(addFlightRequest.getFrom());
        airportList.add(addFlightRequest.getTo());

        Flight newFlight = new Flight(id, addFlightRequest);
        listOfFlights.add(newFlight);
        return newFlight;
    }

    public synchronized Flight getFlightById(Long id) {
        Flight flightFound = null;

        for (Flight flight : listOfFlights) {
            if (flight.getId() == id) {
                flightFound = flight;
            }
        }

        if (flightFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return flightFound;
    }

    public PageResult searchFlights(SearchFlightsRequest searchFlightsRequest) {
        List<Flight> foundedFlights = new ArrayList<>();

        for (Flight flight : listOfFlights) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String flightDepartureDate = flight.getDepartureTime().format(formatter);

            if (flight.getFrom().getAirport().equals(searchFlightsRequest.getFrom())
                    && flight.getTo().getAirport().equals(searchFlightsRequest.getTo())
                    && flightDepartureDate.equals(searchFlightsRequest.getDepartureDate())) {
                foundedFlights.add(flight);
            }
        }
        return new PageResult(0, foundedFlights.size(), foundedFlights);
    }

    public synchronized List<Airport> getAirportList(String search) {
        List<Airport> airportSearchResult = new ArrayList<>();

        for (Airport airport : airportList) {
            if (airport.getCountry().toLowerCase().contains(search.toLowerCase().trim())
                    || airport.getCity().toLowerCase().contains(search.toLowerCase().trim())
                    || airport.getAirport().toLowerCase().contains(search.toLowerCase().trim())) {
                airportSearchResult.add(airport);
            }
        }
        return airportSearchResult;
    }

    public synchronized void deleteFlight(Long id) {
        listOfFlights.removeIf(flight -> flight.getId() == id);
        throw new ResponseStatusException(HttpStatus.OK);
    }

    public void clearFlights() {
        listOfFlights.clear();
        airportList.clear();
    }
}
