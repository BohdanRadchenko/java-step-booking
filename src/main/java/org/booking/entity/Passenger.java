package org.booking.entity;

import java.util.HashMap;
import java.util.Map;

public class Passenger extends Entity {
    private final String firstName;
    private final String lastName;

    private final Map<String, Flight> flights = new HashMap<>();

    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getId(), flight);
    }
}
