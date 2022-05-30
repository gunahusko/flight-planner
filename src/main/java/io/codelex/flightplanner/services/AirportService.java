package io.codelex.flightplanner.services;

import io.codelex.flightplanner.classes.Airport;

import java.util.List;

public interface AirportService {

    public List<Airport> searchAirports(String search);
}
