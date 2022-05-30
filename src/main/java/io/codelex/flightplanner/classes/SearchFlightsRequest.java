package io.codelex.flightplanner.classes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SearchFlightsRequest {
    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotNull
    private LocalDate departureDate;

    public SearchFlightsRequest(String from, String to, String departureDate) {
        if (!from.equals(to)) {
            this.from = from;
            this.to = to;
        }
        this.departureDate = LocalDate.parse(departureDate);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
