package org.booking.entity;

import org.booking.helpers.Constants;
import org.booking.helpers.PrettyFormat;
import org.booking.utils.DateUtil;
import org.booking.utils.Parser;

import java.util.List;
import java.util.Set;

public class Booking extends Entity implements Comparable<Booking> {
    private final String code;
    private final long time;
    private final Flight flight;
    private final User creator;
    private final User passenger;

    public Booking(Flight flight, User creator, User passenger) {
        this.code = createCode();
        this.time = DateUtil.of().getMillis();
        this.flight = flight;
        this.creator = creator;
        this.passenger = passenger;
    }

    public Booking(Flight flight, User user) {
        this(flight, user, user);
    }

    private String createCode() {
        String[] split = String.valueOf(String.format("%04d", bookingCounter)).split("");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = split.length - Constants.SLICE_LENGTH; i < split.length; i++) {
            int num;
            try {
                num = Parser.parseInt(split[i]);
            } catch (NumberFormatException ignored) {
                num = 0;
            }

            int idx = split.length - i;
            if (idx <= Constants.WORD_LENGTH) {
                stringBuilder.append(num);
                continue;
            }
            stringBuilder.append((char) ((num % Constants.CHARS) + 'A'));
        }

        return new String(stringBuilder);
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
