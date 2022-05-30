package io.codelex.flightplanner.classes;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "flight_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Airport from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Airport to;

    private String carrier;

    @Column(name = "departure_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(AddFlightRequest addFlightRequest) {
        this.from = addFlightRequest.getFrom();
        this.to = addFlightRequest.getTo();
        this.carrier = addFlightRequest.getCarrier();
        this.departureTime = addFlightRequest.getDepartureTime();
        this.arrivalTime = addFlightRequest.getArrivalTime();
    }

    public Flight(Long id, AddFlightRequest addFlightRequest) {
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