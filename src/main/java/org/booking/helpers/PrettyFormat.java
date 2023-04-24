package org.booking.helpers;

import org.booking.entity.*;
import org.booking.utils.DateUtil;
import org.booking.utils.StringWorker;

public class PrettyFormat {
    private static final Flight TEST_FLIGHT = new Flight(
            DateUtil.of().getMillis(), Airport.KBP, Airport.LHR, Airline.UKRAINE_INTERNATIONAL, Aircraft.B777, 23);
    private static final User TEST_USER = new User("test", "test");
    public static final Booking TEST_BOOKING = new Booking(TEST_FLIGHT, TEST_USER, TEST_USER);
    public static final Airport TEST_AIRPORT = Airport.KBP;
    private static final String SEPARATOR = "|";

    private static String createHeaderString(String th, String tb, boolean center) {
        th = th.trim();
        tb = tb.trim();
        int headLength = th.length();
        int bodyLength = tb.length();
        String[] split = th.split(String.format("\\%s", SEPARATOR));

        if (split.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" ");
            for (int i = 0; i < split.length; i++) {
                String s = split[i].trim();
                if (i == 0 || i == split.length - 1) {
                    stringBuilder.append(s);
                    continue;
                }
                int sc = bodyLength / split.length;
                int sp = Math.abs(sc - s.length() / 2);
                stringBuilder.append(String.format("%s%s%s", " ".repeat(sp), split[i], " ".repeat(sp + 2)));
            }
            stringBuilder.append(" ");
            return new String(stringBuilder);
        }

        int spaceCount = center ? (bodyLength / 2 - headLength / 2) : headLength - bodyLength;
        int space = Math.abs(spaceCount);
        if (center) {
            return String.format(" %s%s%s ", " ".repeat(space), th, " ".repeat(space));
        }

        return String.format(" %s%s ", th, " ".repeat(space));
    }

    public static String createHeader(String[] heads, boolean[] centers, String body) {
        String[] split = body.split(String.format("\\%s", SEPARATOR));

        if (split.length != heads.length || split.length != centers.length) return "";

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String h = heads[i];
            String b = split[i];
            boolean c = centers[i];
            String res = createHeaderString(h, b, c);
            if (i == split.length - 1) {
                stringBuilder.append(res);
            } else {
                stringBuilder.append(String.format("%s%s", res, SEPARATOR));
            }
        }
        return new String(stringBuilder);
    }

    public static String flightShort(Flight flight) {
        String depTime = DateUtil.of(flight.getDepartureTimeStamp()).formatter("yyyy-MM-dd HH:mm");
        String from = String.format("%s", StringWorker.toUpperCase(flight.getFrom().city));
        String to = String.format("%s", StringWorker.toUpperCase(flight.getTo().city));
        return String.format("%s %s %s %s %-12s ---> %12s\n", flight.getCode(), SEPARATOR, depTime, SEPARATOR, from, to);
    }

    public static String flight(Flight flight) {
        String f = flightShort(flight);
        String aLine = String.format("%s", StringWorker.toUpperCase(flight.getAirline().legalName));
        return String.format("%s %s %s\n", f, SEPARATOR, aLine);
    }

    public static String flightTime(Flight flight) {
        String f = flightShort(flight);
        String arrTime = DateUtil.of(flight.getArrivalTimeStamp()).formatter("yyyy-MM-dd HH:mm");
        return String.format("%s %s %s\n", f, SEPARATOR, arrTime);
    }

    public static String flightFull(Flight flight) {
        String f = flightTime(flight);
        String aircraft = flight.getAircraft().code;
        String seats = String.format("%-3d", flight.getFreeSeats());
        String aLine = String.format("%s", StringWorker.toUpperCase(flight.getAirline().legalName));
        return String.format("%s %s %s %s %s %s %s\n", f, SEPARATOR, seats, SEPARATOR, aircraft, SEPARATOR, aLine);
    }

    public static String flightHeadShort() {
        String[] heads = new String[]{"CODE", "TIME", "FROM | ---> | TO"};
        boolean[] centers = new boolean[]{true, true, false};
        return createHeader(heads, centers, flightShort(TEST_FLIGHT));
    }

    public static String flightHead() {
        return String.format("%s%s%s\n", flightHeadShort(), SEPARATOR, " AIRLINE");
    }

    public static String flightTimeHead() {
        String[] heads = new String[]{"CODE", "DEP TIME", "FROM | ---> | TO", "ARR TIME"};
        boolean[] centers = new boolean[]{true, true, false, true};
        return createHeader(heads, centers, flightTime(TEST_FLIGHT));
    }


    public static String flightFullHead() {
        String[] heads = new String[]{"CODE", "DEP TIME", "FROM | ---> | TO", "ARR TIME", "FPS", "AIRC", "AIRLINE"};
        boolean[] centers = new boolean[]{true, true, false, true, true, true, false};
        return createHeader(heads, centers, flightFull(TEST_FLIGHT));
    }

    public static String booking(Booking booking) {
        return String.format("%s %-6s %s %s", SEPARATOR, booking.getCode(), SEPARATOR, flightTime(booking.getFlight()));
    }

    public static String bookingHead() {
        return String.format("%s %-6s %s %s\n", SEPARATOR, "ID", SEPARATOR, flightTimeHead());
    }

    public static String airport(Airport airport) {
        return String.format("%s %s %s %-17s %-2s %s %s %-12s",
                SEPARATOR,
                airport.code,
                SEPARATOR,
                airport.country,
                SEPARATOR,
                airport.countryShort,
                SEPARATOR,
                airport.city);
    }

    public static String airportHead() {
        return String.format("%s %-3s %s %-23s %s %-12s",
                SEPARATOR,
                "AI",
                SEPARATOR,
                "COUNTRY",
                SEPARATOR,
                "CITY"
        );
    }
}
