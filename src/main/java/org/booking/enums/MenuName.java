package org.booking.enums;

public enum MenuName {
    FLIGHT_SCHEDULES("Flight schedules"),
    FLIGHT_INFO("View flight information"),
    BOOKING("Search & booking"),
    BOOKING_CANCEL("Cancel the reservation"),
    BOOKING_VIEW("View bookings"),
    BOOKING_VIEW_LOGIN("My bookings"),
    LOGIN("Login"),
    REGISTER("Registration"),
    GUEST("Continue as Guest"),
    LOGOUT("Logout");

    public final String title;

    MenuName(String title) {
        this.title = title;
    }
}
