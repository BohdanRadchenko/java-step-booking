package org.booking.entity;

import org.booking.helpers.Utm;
import org.booking.utils.DateUtil;
import org.booking.utils.StringWorker;

import java.util.*;

public class Flight extends Entity implements Comparable<Flight> {
    public long departureTimeStamp;
    public long arrivalTimeStamp;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private Aircraft aircraft;
    private String flightId;
    private Set<String> passengers;
    private int freeSeats;
    private final String code;


    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport, Airline airline,
                  Aircraft aircraft, int flightId, Set<String> reserved) {
        this.departureTimeStamp = departureTimeStamp;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airline = airline;
        this.flightId = String.format("%03d", flightId);
        this.passengers = reserved;
        this.aircraft = aircraft;
        this.freeSeats = aircraft.seats - reserved.size();
        this.arrivalTimeStamp = arrivalTimeMls();
        this.code = String.format("%s%s", this.airline.code, this.flightId);
    }

    public Flight(long departureTimeStamp, Airport departureAirport, Airport arrivalAirport,
                  Airline airline, Aircraft aircraft, int flightId) {
        this(departureTimeStamp, departureAirport, arrivalAirport, airline, aircraft, flightId, new HashSet<>());
    }

    private long arrivalTimeMls() {
        int distance = Utm.distance(departureAirport, arrivalAirport);
        int cruiserTime = distance / aircraft.speed * 60 * 60 * 100;
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

    public String prettyFormat() {
        String depTime = DateUtil.of(departureTimeStamp).formatter("yyyy-MM-dd HH:mm");
        String from = String.format("%s", StringWorker.toUpperCase(departureAirport.city));
        String to = String.format("%s", StringWorker.toUpperCase(arrivalAirport.city));
        String aLine = String.format("%s", StringWorker.toUpperCase(airline.legalName));
        return String.format("%s | %s | %-12s ---> %12s | %s\n", code, depTime, from, to, aLine);
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

