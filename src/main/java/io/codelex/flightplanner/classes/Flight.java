package io.codelex.flightplanner.classes;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private Long id;
    private Airport from;
    private Airport to;
    private String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight(Long id, AddFlightRequest addFlightRequest) {
        this.id = id;
        this.from = addFlightRequest.getFrom();
        this.to = addFlightRequest.getTo();
        this.carrier = addFlightRequest.getCarrier();
        this.departureTime = addFlightRequest.getDepartureTime();
        this.arrivalTime = addFlightRequest.getArrivalTime();
    }

    public boolean isEqualFlight(AddFlightRequest addFlightRequest) {
        return (this.from.equals(addFlightRequest.getFrom())
                && this.to.equals(addFlightRequest.getTo())
                && this.carrier.equals(addFlightRequest.getCarrier())
                && this.departureTime.equals(addFlightRequest.getDepartureTime())
                && this.arrivalTime.equals(addFlightRequest.getArrivalTime()));
    }

    public boolean matchesSearchRequest(SearchFlightsRequest request) {
        return this.from.getAirport().equals(request.getFrom())
                && this.to.getAirport().equals(request.getTo())
                && this.departureTime.toLocalDate().equals(request.getDepartureDate());
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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