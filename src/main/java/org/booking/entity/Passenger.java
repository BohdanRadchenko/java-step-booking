package org.booking.entity;

import java.util.HashSet;
import java.util.Set;

public class Passenger extends Entity {
    private final String firstName;
    private final String lastName;

    private final Set<String> flights = new HashSet<>();

    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addFlight(Flight flight) {
        if (flights.contains(flight.getId())) return false;
        flights.add(flight.getId());
        return true;
    }
}
