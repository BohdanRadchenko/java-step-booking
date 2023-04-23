package org.booking.helpers;

import org.booking.entity.*;
import org.booking.utils.DateUtil;
import org.booking.utils.StringWorker;

public class PrettyFormat {
    private static final Flight testFlight = new Flight(
            DateUtil.of().getMillis(), Airport.KBP, Airport.LHR, Airline.UKRAINE_INTERNATIONAL, Aircraft.B777, 23);
    private static final User testUser = new User("test", "test");
    private static final Booking testBooking = new Booking(testFlight, testUser, testUser);
    private static final String separator = "|";

    private static String createHeaderString(String th, String tb, boolean center) {
        th = th.trim();
        tb = tb.trim();
        int headLength = th.length();
        int bodyLength = tb.length();
        String[] split = th.split(String.format("\\%s", separator));

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
        String[] split = body.split(String.format("\\%s", separator));

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
                stringBuilder.append(String.format("%s%s", res, separator));
            }
        }
        return new String(stringBuilder);
    }

    public static String flightShort(Flight flight) {
        String depTime = DateUtil.of(flight.getDepartureTimeStamp()).formatter("yyyy-MM-dd HH:mm");
        String from = String.format("%s", StringWorker.toUpperCase(flight.getFrom().city));
        String to = String.format("%s", StringWorker.toUpperCase(flight.getTo().city));
        return String.format("%s %s %s %s %-12s ---> %12s\n", flight.getCode(), separator, depTime, separator, from, to);
    }

    public static String flight(Flight flight) {
        String f = flightShort(flight);
        String aLine = String.format("%s", StringWorker.toUpperCase(flight.getAirline().legalName));
        return String.format("%s %s %s\n", f, separator, aLine);
    }

    public static String flightTime(Flight flight) {
        String f = flightShort(flight);
        String arrTime = DateUtil.of(flight.getArrivalTimeStamp()).formatter("yyyy-MM-dd HH:mm");
        return String.format("%s %s %s\n", f, separator, arrTime);
    }

    public static String flightFull(Flight flight) {
        String f = flightTime(flight);
        String seats = String.format("%-3d", flight.getFreeSeats());
        String aLine = String.format("%s", StringWorker.toUpperCase(flight.getAirline().legalName));
        return String.format("%s %s %s %s %s\n", f, separator, seats, separator, aLine);
    }

    public static String flightHeadShort() {
        String[] heads = new String[]{"CODE", "TIME", "FROM | ---> | TO",};
        boolean[] centers = new boolean[]{true, true, false,};
        return createHeader(heads, centers, flightShort(testFlight));
    }

    public static String flightHead() {
        return String.format("%s%s%s\n", flightHeadShort(), separator, " AIRLINE");
    }

    public static String flightTimeHead() {
        String[] heads = new String[]{"CODE", "DEP TIME", "FROM | ---> | TO", "ARR TIME"};
        boolean[] centers = new boolean[]{true, true, false, true};
        return createHeader(heads, centers, flightTime(testFlight));
    }


    public static String flightHeadFull() {
        String[] heads = new String[]{"CODE", "DEP TIME", "FROM | ---> | TO", "ARR TIME", "FPS", "AIRLINE"};
        boolean[] centers = new boolean[]{true, true, false, true, true, false};
        return createHeader(heads, centers, flightFull(testFlight));
    }

    public static String booking(Booking booking) {
        return String.format("| %-5s%s %s", booking.getCode(), separator, flightTime(booking.getFlight()));
    }

    public static String bookingHead() {
        return String.format("| %-5s%s %s\n", "ID", separator, flightTimeHead());
    }

}
