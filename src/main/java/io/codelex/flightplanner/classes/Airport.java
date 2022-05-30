package io.codelex.flightplanner.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class Airport {
    @NotEmpty
    private String country;
    @NotEmpty
    private String city;
    @Id
    @NotEmpty
    @Column(name = "airport_id")
    private String airport;

    public Airport(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport.toUpperCase();
    }

    public Airport() {
    }

    public boolean isEqualAirport(Airport airport) {
        return this.getCountry().trim().equalsIgnoreCase(airport.getCountry().trim())
                && getCity().trim().equalsIgnoreCase(airport.getCity().trim())
                && getAirport().trim().equalsIgnoreCase(airport.getAirport().trim());
    }

    public boolean matchesSearchRequest(String search) {
        return this.getCountry().toLowerCase().contains(search.toLowerCase().trim())
                || getCity().toLowerCase().contains(search.toLowerCase().trim())
                || getAirport().toLowerCase().contains(search.toLowerCase().trim());
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Airport)) {
            return false;
        }
        Airport airport1 = (Airport) o;
        return getCountry().equals(airport1.getCountry())
                && getCity().equals(airport1.getCity())
                && getAirport().equals(airport1.getAirport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity(), getAirport());
    }

    @Override
    public String toString() {
        return "Airport{"
                + "country='" + country + '\''
                + ", city='" + city + '\''
                + ", airport='" + airport + '\'' + '}';
    }
}
