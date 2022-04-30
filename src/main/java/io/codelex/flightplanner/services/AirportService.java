package io.codelex.flightplanner.services;

import io.codelex.flightplanner.classes.Airport;
import io.codelex.flightplanner.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> searchAirports(String search) {
        return airportRepository.searchAirports(search);
    }
}
