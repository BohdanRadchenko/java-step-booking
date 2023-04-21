package org.booking.entity;

import org.booking.utils.DateUtil;

public class Booking extends Entity {
    private final long time;
    private final Flight flight;
    private final Passenger passenger;

    public Booking(Flight flight, Passenger passenger) {
        this.time = DateUtil.of().getMillis();
        this.flight = flight;
        this.passenger = passenger;
    }
}
