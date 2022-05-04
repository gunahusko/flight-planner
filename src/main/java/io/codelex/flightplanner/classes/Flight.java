package io.codelex.flightplanner.classes;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private final Long id;
    private final Airport from;
    private final Airport to;
    private final String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime arrivalTime;

    public Flight(Long id, AddFlightRequest addFlightRequest) {
        this.id = id;
        this.from = addFlightRequest.getFrom();
        this.to = addFlightRequest.getTo();
        this.carrier = addFlightRequest.getCarrier();
        this.departureTime = addFlightRequest.getDepartureTime();
        this.arrivalTime = addFlightRequest.getArrivalTime();
    }

    public boolean areEqualFlights(AddFlightRequest addFlightRequest) {
        return (from.equals(addFlightRequest.getFrom())
                && to.equals(addFlightRequest.getTo())
                && carrier.equals(addFlightRequest.getCarrier())
                && departureTime.equals(addFlightRequest.getDepartureTime())
                && arrivalTime.equals(addFlightRequest.getArrivalTime()));
    }

    public boolean matchesSearchRequest(SearchFlightsRequest request) {
        return from.getAirport().equals(request.getFrom())
                && to.getAirport().equals(request.getTo())
                && departureTime.toLocalDate().equals(request.getDepartureDate());
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flight)) {
            return false;
        }
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id)
                && getFrom().equals(flight.getFrom())
                && getTo().equals(flight.getTo())
                && Objects.equals(getCarrier(), flight.getCarrier())
                && getDepartureTime().equals(flight.getDepartureTime())
                && getArrivalTime().equals(flight.getArrivalTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFrom(), getTo(), getCarrier(), getDepartureTime(), getArrivalTime());
    }

    @Override
    public String toString() {
        return "Flight{"
                + "id=" + id
                + ", from=" + from
                + ", to=" + to
                + ", carrier='" + carrier + '\''
                + ", departureTime=" + departureTime
                + ", arrivalTime=" + arrivalTime + '}';
    }
}