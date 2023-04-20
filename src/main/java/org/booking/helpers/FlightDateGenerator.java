package org.booking.helpers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Random;

public class FlightDateGenerator {

    private static final int MIN_DAYS_TO_FLIGHT = 1;
    private static final int MAX_DAYS_TO_FLIGHT = 30;

    private static final LocalTime DEPARTURE_TIME = LocalTime.of(8, 0);

    private static final int MIN_DURATION_HOURS = 1;
    private static final int MAX_DURATION_HOURS = 10;

    private Random random;

    public FlightDateGenerator() {
        random = new Random();
    }

    public LocalDateTime generateFlightDate() {
        LocalDate departureDate = LocalDate.now().plusDays(random.nextInt(MAX_DAYS_TO_FLIGHT - MIN_DAYS_TO_FLIGHT + 1) + MIN_DAYS_TO_FLIGHT);
        LocalTime departureTime = DEPARTURE_TIME;
        Duration flightDuration = Duration.ofHours(random.nextInt(MAX_DURATION_HOURS - MIN_DURATION_HOURS + 1) + MIN_DURATION_HOURS);
        LocalDateTime arrivalDateTime = LocalDateTime.of(departureDate, departureTime).plus(flightDuration);
        return arrivalDateTime;
    }

    public LocalDateTime generateArrivalDate(LocalDateTime departureDate, int hoursToAdd) {
        return departureDate.plusHours(hoursToAdd);
    }

}