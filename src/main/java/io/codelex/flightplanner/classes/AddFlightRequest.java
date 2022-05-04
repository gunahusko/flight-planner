package io.codelex.flightplanner.classes;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddFlightRequest {
    @NotNull
    @Valid
    private final Airport from;
    @NotNull
    @Valid
    private final Airport to;
    @NotEmpty
    private final String carrier;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime departureTime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime arrivalTime;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AddFlightRequest(Airport from, Airport to, String carrier, String departureTime, String arrivalTime) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = LocalDateTime.parse(departureTime, formatter);
        this.arrivalTime = LocalDateTime.parse(arrivalTime, formatter);
    }

    public boolean areEqualAirports() {
        return from.isEqualAirport(to);
    }

    public boolean datesAreCorrect() {
        return departureTime.isBefore(arrivalTime);
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public String getCarrier() {
        return carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
}
