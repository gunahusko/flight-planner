package io.codelex.flightplanner.services;

import io.codelex.flightplanner.classes.AddFlightRequest;
import io.codelex.flightplanner.classes.Airport;
import io.codelex.flightplanner.classes.Flight;
import io.codelex.flightplanner.repositories.FlightsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FlightsServiceImplTest {

    @Mock
    FlightsRepository flightsRepository;

    @InjectMocks
    FlightsServiceImpl flightsServiceImpl;

    @Test
    public void testAddFlight() {
        Airport from = new Airport("Latvia", "Riga", "RIX");
        Airport to = new Airport("Estonia", "Tallin", "EST");
        String carrier = "airBaltic";
        String departureTime = "2022-05-02 08:30";
        String arrivalTime = "2022-05-03 10:30";

        AddFlightRequest addFlightRequest = new AddFlightRequest(from, to, carrier, departureTime, arrivalTime);

        Mockito.doAnswer(invocation -> {
            AddFlightRequest request = invocation.getArgument(0);
            Assertions.assertEquals(addFlightRequest, request);
            return new Flight(67L, request);
        }).when(flightsRepository).addFlight(addFlightRequest);

        Flight flight = flightsServiceImpl.addFlight(addFlightRequest);

        Mockito.verify(flightsRepository).addFlight(addFlightRequest);
    }
}
