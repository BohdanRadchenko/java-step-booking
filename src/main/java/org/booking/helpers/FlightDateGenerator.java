package org.booking.helpers;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class FlightDateGenerator {

    // Минимальное и максимальное количество дней до даты вылета
    private static final int MIN_DAYS_TO_FLIGHT = 1;
    private static final int MAX_DAYS_TO_FLIGHT = 30;

    // Время вылета
    private static final LocalTime DEPARTURE_TIME = LocalTime.of(8, 0);
    private static final int MIN_DURATION_HOURS = 1;
    private static final int MAX_DURATION_HOURS = 10;

    public Random random;

    public FlightDateGenerator() {
        random = new Random();
    }

    // Метод, который генерирует случайную дату вылета
    public String generateFlightDateAsString() {
        int daysToAdd = random.nextInt(MAX_DAYS_TO_FLIGHT - MIN_DAYS_TO_FLIGHT + 1) + MIN_DAYS_TO_FLIGHT;
        LocalDate departureDate = LocalDate.now().plusDays(daysToAdd);
        LocalTime departureTime = DEPARTURE_TIME;
        int hoursToAdd = random.nextInt(MAX_DURATION_HOURS - MIN_DURATION_HOURS + 1) + MIN_DURATION_HOURS;
        LocalDateTime arrivalDateTime = LocalDateTime.of(departureDate, departureTime).plusHours(hoursToAdd);
        return arrivalDateTime.toString();
    }

    // Метод, который генерирует дату прибытия на основе даты вылета и количества часов полета
    public String generateArrivalDateAsString(String departureDate, int hoursToAdd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime departureDateTime = LocalDateTime.parse(departureDate, formatter);
        LocalDateTime arrivalDateTime = departureDateTime.plusHours(hoursToAdd);
        return arrivalDateTime.toString();
    }
    public String generateFlightDateInMillis() {
        int daysToAdd = random.nextInt(MAX_DAYS_TO_FLIGHT - MIN_DAYS_TO_FLIGHT + 1) + MIN_DAYS_TO_FLIGHT;
        LocalDate departureDate = LocalDate.now().plusDays(daysToAdd);
        LocalTime departureTime = DEPARTURE_TIME;
        int hoursToAdd = random.nextInt(MAX_DURATION_HOURS - MIN_DURATION_HOURS + 1) + MIN_DURATION_HOURS;
        LocalDateTime arrivalDateTime = LocalDateTime.of(departureDate, departureTime).plusHours(hoursToAdd);
        return millisToString(arrivalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    public String millisToString(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}