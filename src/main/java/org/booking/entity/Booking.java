package org.booking.entity;

import org.booking.helpers.Constants;
import org.booking.utils.DateUtil;
import org.booking.utils.Parser;

import java.util.List;
import java.util.Set;

public class Booking extends Entity {
    private final String code;
    private final long time;
    private final List<Flight> flights;
    private final User creator;
    private final User passenger;

    {
        bookingCounter++;
    }

    public Booking(List<Flight> flights, User creator, User passenger) {
        this.code = createCode();
        this.time = DateUtil.of().getMillis();
        this.flights = flights;
        this.creator = creator;
        this.passenger = passenger;
    }

    public Booking(List<Flight> flights, User user) {
        this(flights, user, user);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Flight.prettyFormatShortHead());
        flights.forEach(f -> {
            stringBuilder.append(f.prettyFormatShort());
            stringBuilder.append("\n");
        });
        return new String(stringBuilder);
    }
}
