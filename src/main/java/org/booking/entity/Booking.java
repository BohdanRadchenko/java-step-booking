package org.booking.entity;

import org.booking.helpers.PrettyFormat;
import org.booking.utils.DateUtil;

public class Booking extends Entity implements Comparable<Booking> {
    private final String code;
    private final long time;
    private final Flight flight;
    private final User creator;
    private final User passenger;

    public Booking(Flight flight, User creator, User passenger) {
        this.time = DateUtil.of().getMillis();
        this.flight = flight;
        this.creator = creator;
        this.passenger = passenger;
        this.code = createCode();
    }

    public Booking(Flight flight, User user) {
        this(flight, user, user);
    }

    private String createCode() {
        final int maxBookingIntLength = 4;
        int nano = DateUtil.of(time).getNano();

        StringBuilder sb = new StringBuilder();
        sb.append(this.flight.getFrom().countryShort);
        sb.append(bookingCounter);

        String[] splitTime = String.valueOf(nano).split("");

        for (int i = 0; i < (maxBookingIntLength - String.valueOf(bookingCounter).length()); i++) {
            sb.append(splitTime[i]);
        }
        return new String(sb);
    }

    public String getCode() {
        return code;
    }

    public String getCreatorId() {
        return creator.getId();
    }

    public String getPassengerId() {
        return passenger.getId();
    }

    public Flight getFlight() {
        return flight;
    }

    public void cancel() {
        this.flight.removePassenger(this.passenger);
        this.passenger.cancelBooking(this);
    }

    @Override
    public String toString() {
        return PrettyFormat.flightShort(flight);
    }

    @Override
    public int compareTo(Booking that) {
        if (this.time < that.time) return -1;
        if (this.time > that.time) return +1;
        return 0;
    }
}
