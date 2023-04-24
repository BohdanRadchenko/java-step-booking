package org.booking.helpers;

public class MenuDescription {
    public static String flightInfo() {
        String row1 = "Enter the FLIGHT ID in AAXXXX format or 'Back' to main menu\n";
        return String.format("%s", row1);
    }

    public static String booking() {
        return String.format("'%d' - show airports or 'Back' to main menu", Constants.HELP_CODE);
    }

    public static String bookingCancel() {
        return "Enter the Booking ID (AAXXXX) or 'Back' to main menu";
    }

    public static String bookingView() {
        return "Enter passenger full name or 'Back' to main menu";
    }
}
