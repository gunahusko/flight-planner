package io.codelex.flightplanner.repositories;

import io.codelex.flightplanner.classes.Airport;
import io.codelex.flightplanner.classes.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlightDatabaseRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(
            Airport from_id,
            Airport to_id,
            String Carrier,
            LocalDateTime departure_time,
            LocalDateTime arrival_time);

    @Query("select f from Flight f "
            + "where f.from.airport = :from_id "
            + "and f.to.airport = :to_id "
            + "and f.departureTime >= :departure_time ")
    Optional<Flight> findByFromAndToAndDepartureTime(
            @Param("from_id") String from,
            @Param("to_id") String to_id,
            @Param("departure_time") LocalDateTime departure_time
    );
}
