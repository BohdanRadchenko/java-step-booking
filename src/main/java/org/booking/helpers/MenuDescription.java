package org.booking.helpers;

public class MenuDescription {
    public static String flightInfo() {
        String row1 = "Enter the FLIGHT ID in AAxxxx format.\n";
        String row2 = "AA - airline\n";
        String row3 = "xxxx - flight number\n";
        String row4 = "or 'Back' to main menu";
        return String.format("%s%s%s%s", row1, row2, row3, row4);
    }
}
