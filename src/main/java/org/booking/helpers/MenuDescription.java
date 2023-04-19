package org.booking.helpers;

public class MenuDescription {
    public static String flightInfo() {
        String row1 = "Enter the FLIGHT ID in AAxxxx format.\n";
        String row2 = "AA - airline, xxxx - flight number.\n";
        String row3 = "or 'Back' to main menu";
        return String.format("%s%s%s", row1, row2, row3);
    }

    public static String booking() {
        return "Enter FLIGHT data or Exit (0, q).\n";
    }
}
