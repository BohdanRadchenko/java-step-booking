package org.booking.entity;

import org.booking.helpers.Utm;
import org.booking.utils.DateUtil;
import org.booking.utils.StringWorker;

import java.time.Duration;
import java.time.Period;
import java.util.*;

public class Flight extends Entity implements Comparable<Flight> {
    public long departureTimeStamp;
    public long arrivalTimeStamp;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Aircraft aircraft;
    private String flightId;
    private Set<User> passengers = new HashSet<>();
    private int freeSeats;
    private final String code;

    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport, Airline airline,
                  Aircraft aircraft, int flightId, Set<User> passengers) {
        this.departureTimeStamp = departureTimeStamp;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.flightId = String.format("%03d", flightId);
        if (passengers != null) {
            this.passengers = passengers;
        }
        this.aircraft = aircraft;
        this.freeSeats = aircraft.seats - this.passengers.size();
        this.arrivalTimeStamp = arrivalTimeMls();
        this.code = String.format("%s%s", this.airline.code, this.flightId);
    }

    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport,
                  Airline airline, Aircraft aircraft, int flightId) {
        this(departureTimeStamp, departureAirport, arrivalAirport, airline, aircraft, flightId, new HashSet<>());
    }

    public boolean addPassenger(User passenger) {
        return passengers.add(passenger);
    }

    private long arrivalTimeMls() {
        int distance = Utm.distance(departureAirport, arrivalAirport);
        long cruiserTime = (long) (distance / aircraft.speed) * 3600000;
        return this.departureTimeStamp + cruiserTime + 3600000;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public long getDepartureTimeStamp() {
        return this.departureTimeStamp;
    }

    public long getArrivalTimeStamp() {
        return this.arrivalTimeStamp;
    }

    public String getCode() {
        return code;
    }

    public Airport getFrom() {
        return departureAirport;
    }

    public Airport getTo() {
        return arrivalAirport;
    }

    public static String prettyFormatShortHead() {
        // code head
        String code = String.format("%-6s|", "CODE");

        // time head
        int dateLength = 16;
        String timeTitle = "TIME";
        int timeSpace = (dateLength / 2 - timeTitle.length() / 2) + 1;
        String time = String.format("%s%s%s|", " ".repeat(timeSpace), timeTitle, " ".repeat(timeSpace));

        // from head
        int fromToLength = 32;
        String fromHead = "FROM";
        String toHead = "TO";
        int fromToSpace = fromToLength - fromHead.length() - toHead.length() - 2;
        String fromTo = String.format(" %s%s%s |", fromHead, " ".repeat(fromToSpace), toHead);

        return String.format("%s%s%s\n",
                code,
                time,
                fromTo
        );
    }

    public static String prettyFormatHead() {
        String shortHead = prettyFormatShortHead();
        return String.format("%s%s\n", shortHead, " AIRLINE");
    }

    public String prettyFormatShort() {
        String depTime = DateUtil.of(departureTimeStamp).formatter("yyyy-MM-dd HH:mm");
        String from = String.format("%s", StringWorker.toUpperCase(departureAirport.city));
        String to = String.format("%s", StringWorker.toUpperCase(arrivalAirport.city));
        return String.format("%s | %s | %-12s ---> %12s\n", code, depTime, from, to);
    }

    public String prettyFormat() {
        String f = prettyFormatShort();
        String aLine = String.format("%s", StringWorker.toUpperCase(airline.legalName));
        return String.format("%s | %s\n", f, aLine);
    }

    public String prettyFormatFull() {
        String timeFrom = DateUtil.of(departureTimeStamp).formatter("yyyy-MM-dd HH:mm");
        String timeTo = DateUtil.of(arrivalTimeStamp).formatter("yyyy-MM-dd HH:mm");
        Duration diff = DateUtil.betweenDuration(departureTimeStamp, arrivalTimeStamp);
        Period diffPeriod = DateUtil.betweenPeriod(departureTimeStamp, arrivalTimeStamp);
        long diffMinutes = diff.toMinutes() - (diff.toHours() * 60);
        String airTime = String.format("%02d:%02d:%02d", diff.toHours(), diff.toHours(), diffMinutes);

        String from = String.format("%s", StringWorker.toUpperCase(departureAirport.city));
        String to = String.format("%s", StringWorker.toUpperCase(arrivalAirport.city));
        String f = prettyFormatShort();
        String aLine = String.format("%s", StringWorker.toUpperCase(airline.legalName));
        return String.format("%s | %s\n", f, aLine);
    }

    public String toString() {
        String departureTime = String.format("departureTimeStamp=%d", departureTimeStamp);
        String arrivalTime = String.format("arrivalTimeStamp=%d", arrivalTimeStamp);
        String depAirport = String.format("departureAirport=%s", departureAirport);
        String arrAirport = String.format("arrivalAirport=%s", arrivalAirport);
        String airLine = String.format("airline=%s", airline);
        String airCraft = String.format("aircraft=%s", aircraft);
        String fltId = String.format("flightId=%s", flightId);
        String res = String.format("reserved=%s", passengers);
        String freeSeat = String.format("freeSeats=%d", freeSeats);
        return String.format("Flight{%s,%s,%s,%s, %s,%s,%s,%s,%s}",
                departureTime, arrivalTime, depAirport, arrAirport, airLine, airCraft, fltId, res, freeSeat);
    }

    @Override
    public int compareTo(Flight that) {
        if (this.departureTimeStamp < that.departureTimeStamp) return -1;
        if (this.departureTimeStamp > that.departureTimeStamp) return +1;
        return 0;
    }
}

