package org.booking.enums;

public enum MenuName {
    FLIGHT_SCHEDULES("Departure board"),
    FLIGHT_INFO("Flight info"),
    BOOKING("Search & booking"),
    BOOKING_CANCEL("Cancel booking"),
    BOOKING_VIEW("Bookings"),
    BOOKING_VIEW_LOGIN("My bookings"),
    LOGIN("Sigh in"),
    REGISTER("Sign up"),
    GUEST("Continue as Guest"),
    LOGOUT("Logout");

    public final String title;

    MenuName(String title) {
        this.title = title;
    }
}
