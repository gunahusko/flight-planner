package io.codelex.flightplanner.services;

import io.codelex.flightplanner.classes.AddFlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractFlightService {

    public void dataVerification(AddFlightRequest addFlightRequest) {

        if (addFlightRequest.areEqualAirports()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!addFlightRequest.datesAreCorrect()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
