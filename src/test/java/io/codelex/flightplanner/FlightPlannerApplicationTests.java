package io.codelex.flightplanner;

import io.codelex.flightplanner.classes.AddFlightRequest;
import io.codelex.flightplanner.classes.Airport;
import io.codelex.flightplanner.classes.Flight;
import io.codelex.flightplanner.controllers.AdminClientController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlightPlannerApplicationTests {

	@Autowired
	AdminClientController adminClientController;

	@Test
	void addFlightsTest() {
		Airport from = new Airport("Latvia", "Riga", "RIX");
		Airport to = new Airport("Estonia", "Tallin", "EST");
		String departureTime = "2022-05-02 08:30";
		String arrivalTime = "2022-05-03 10:30";

		AddFlightRequest addFlightRequest = new AddFlightRequest(from, to, "AirBaltic", departureTime, arrivalTime);
		Flight flight = adminClientController.addFlight(addFlightRequest);

		Assertions.assertNotNull(flight.getId());
	}

}
