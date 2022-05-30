package io.codelex.flightplanner.services;

import io.codelex.flightplanner.classes.Airport;
import io.codelex.flightplanner.repositories.AirportDatabaseRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "flight-planner", name = "store-type", havingValue = "database")
public class AirportDatabaseServiceImpl implements AirportService {

    private final AirportDatabaseRepository airportDatabaseRepository;

    public AirportDatabaseServiceImpl(AirportDatabaseRepository airportRepository) {
        this.airportDatabaseRepository = airportRepository;
    }

    @Override
    @Transactional
    public List<Airport> searchAirports(String search) {
//        return airportDatabaseRepository.findAll()
//                .stream()
//                .filter(airport -> airport.matchesSearchRequest(search))
//                .collect(Collectors.toList());

    return airportDatabaseRepository.searchAirport(search.toLowerCase().trim());
    }

}
