package org.booking.entity;

import org.booking.components.FlightDateGenerator;

import java.time.LocalDateTime;

public class Flight {
    private LocalDateTime flightDate;
    private LocalDateTime arrivalDate;


    public Flight(LocalDateTime flightDate, LocalDateTime arrivalDate) {
        this.flightDate = flightDate;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightDate=" + flightDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
