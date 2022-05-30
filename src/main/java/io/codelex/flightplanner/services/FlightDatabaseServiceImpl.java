package io.codelex.flightplanner.services;

import io.codelex.flightplanner.classes.*;
import io.codelex.flightplanner.repositories.AirportDatabaseRepository;
import io.codelex.flightplanner.repositories.FlightDatabaseRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "flight-planner", name = "store-type", havingValue = "database")
public class FlightDatabaseServiceImpl extends AbstractFlightService implements FlightsService {

    private final FlightDatabaseRepository flightDatabaseRepository;
    private final AirportDatabaseRepository airportDatabaseRepository;

    public FlightDatabaseServiceImpl(FlightDatabaseRepository flightDatabaseRepository, AirportDatabaseRepository airportDatabaseRepository) {
        this.flightDatabaseRepository = flightDatabaseRepository;
        this.airportDatabaseRepository = airportDatabaseRepository;
    }

    @Override
    @Transactional
    public Flight addFlight(AddFlightRequest addFlightRequest) {
        dataVerification(addFlightRequest);

        Optional<Flight> sameFlight = flightDatabaseRepository.findByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(
                addFlightRequest.getFrom(),
                addFlightRequest.getTo(),
                addFlightRequest.getCarrier(),
                addFlightRequest.getDepartureTime(),
                addFlightRequest.getArrivalTime());

        if (sameFlight.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Optional<Airport> maybeAirportFrom = airportDatabaseRepository.findById(addFlightRequest.getFrom().getAirport());
        Airport airportFrom = maybeAirportFrom.orElse(airportDatabaseRepository.save(addFlightRequest.getFrom()));

        Optional<Airport> maybeAirportTo = airportDatabaseRepository.findById(addFlightRequest.getTo().getAirport());
        Airport airportTo = maybeAirportTo.orElse(airportDatabaseRepository.save(addFlightRequest.getTo()));

        Flight flight = new Flight(addFlightRequest);
        flight.setFrom(airportFrom);
        flight.setTo(airportTo);
        return flightDatabaseRepository.save(flight);
    }

    @Override
    public void dataVerification(AddFlightRequest addFlightRequest) {
        super.dataVerification(addFlightRequest);
    }

    @Override
    @Transactional
    public Flight getFlightById(Long id) {
        return flightDatabaseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional
    public PageResult searchFlights(SearchFlightsRequest searchFlightsRequest) {
        List<Flight> listOfFlights = new ArrayList<>();

        flightDatabaseRepository.findByFromAndToAndDepartureTime(
                searchFlightsRequest.getFrom(),
                searchFlightsRequest.getTo(),
                LocalDateTime.of(searchFlightsRequest.getDepartureDate(), LocalTime.of(0, 0)))
                .ifPresent(listOfFlights::add);

        return new PageResult(listOfFlights);

    }

    @Override
    public void deleteFlight(Long id) {
        if (flightDatabaseRepository.findById(id).isPresent()) {
            flightDatabaseRepository.deleteById(id);
        }
    }

    @Override
    public void clearFlights() {
        flightDatabaseRepository.deleteAll();
        airportDatabaseRepository.deleteAll();
    }
}
