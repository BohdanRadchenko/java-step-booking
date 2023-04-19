package org.booking.ui.menu;

import org.booking.command.*;
import org.booking.enums.MenuName;
import org.booking.helpers.MenuDescription;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main menu");

        add(MenuName.FLIGHT_SCHEDULES, FlightSchedules.of());
        add(MenuName.FLIGHT_INFO, FlightInfo.of(), MenuDescription.flightInfo());
        add(MenuName.FLIGHT_BOOKING, Booking.of());
        add(MenuName.BOOKING_CANCEL, BookingCancel.of());
        add(MenuName.BOOKING_VIEW, BookingView.of());
    }
}
