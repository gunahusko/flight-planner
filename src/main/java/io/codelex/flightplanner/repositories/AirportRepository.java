package io.codelex.flightplanner.repositories;

import io.codelex.flightplanner.classes.Airport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AirportRepository {

    private final List<Airport> airportList = new ArrayList<>();

    public void addAirport(Airport airport) {
        if (!airportList.contains(airport)) {
            airportList.add(airport);
        }
    }

    public synchronized List<Airport> searchAirports(String search) {
        return airportList
                .stream()
                .filter(airport -> airport.matchesSearchRequest(search))
                .collect(Collectors.toList());
    }

    public void clearAirportList() {
        airportList.clear();
    }
}