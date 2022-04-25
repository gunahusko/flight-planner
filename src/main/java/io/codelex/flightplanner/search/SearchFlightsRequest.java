package io.codelex.flightplanner.search;

import javax.validation.constraints.NotEmpty;

public class SearchFlightsRequest {
    @NotEmpty
    private String from;
    @NotEmpty
    private String to;
    @NotEmpty
    private String departureDate;

    public SearchFlightsRequest(String from, String to, String departureDate) {
        if (!from.equals(to)) {
            this.from = from;
            this.to = to;
        }
        this.departureDate = departureDate;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
