package io.codelex.flightplanner.repositories;

import io.codelex.flightplanner.classes.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportDatabaseRepository extends JpaRepository<Airport, String> {

    @Query(
            "select a from Airport a"
                    + " where lower(a.airport) like CONCAT('%', :search, '%')"
                    + " or lower(a.country) like CONCAT('%', :search, '%')"
                    + " or lower(a.city) like CONCAT('%', :search, '%')")
    List<Airport> searchAirport(@Param("search") String search);

}
