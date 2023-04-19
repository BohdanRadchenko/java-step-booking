package org.booking.enums;

public enum MenuName {
    FLIGHT_SCHEDULES("Flight schedules"),
    FLIGHT_INFO("View flight information"),
    BOOKING("Search & booking"),
    BOOKING_CANCEL("Cancel the reservation"),
    BOOKING_VIEW("View bookings"),
    LOGIN("Login");

    public final String title;

    MenuName(String title) {
        this.title = title;
    }
}
