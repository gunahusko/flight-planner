package io.codelex.flightplanner.repositories;

import io.codelex.flightplanner.classes.Airport;
import io.codelex.flightplanner.classes.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlightDatabaseRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(
            Airport from_id,
            Airport to_id,
            String Carrier,
            LocalDateTime departure_time,
            LocalDateTime arrival_time);
}
