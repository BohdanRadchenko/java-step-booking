package org.booking.entity;

import org.booking.utils.DateUtil;

public class Booking extends Entity {
    private final long time;
    private final Flight flight;
    private final User creator;
    private final User passenger;

    public Booking(Flight flight, User creater, User passenger) {
        this.time = DateUtil.of().getMillis();
        this.flight = flight;
        this.creator = creater;
        this.passenger = passenger;
    }

    public Booking(Flight flight, User user) {
        this(flight, user, user);
    }

    public String getCreatorId() {
        return creator.getId();
    }

    public String getPassengerId() {
        return passenger.getId();
    }
}
