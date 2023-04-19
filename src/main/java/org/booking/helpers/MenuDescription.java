package org.booking.helpers;

public class MenuDescription {
    public static String flightInfo() {
        String row1 = "Enter the FLIGHT ID in AAxxxx format.\n";
        String row2 = "AA - airline, xxxx - flight number.\n";
        String row3 = "or 'Back' to main menu";
        return String.format("%s%s%s", row1, row2, row3);
    }

    public static String booking() {
        return "Enter FLIGHT data or 'Back' to main menu";
    }

    public static String bookingCancel() {
        return "Enter the Booking ID or 'Back' to main menu";
    }

    public static String bookingView() {
        return "Enter passenger first name and last name or 'Back' to main menu";
    }
}
