package org.booking.ui.menu;

import org.booking.command.*;
import org.booking.controllers.Controller;
import org.booking.enums.MenuName;
import org.booking.helpers.MenuDescription;

public class MainMenu extends Menu {
    public MainMenu(Controller controller) {
        super("Main menu");
        add(MenuName.FLIGHT_SCHEDULES, FlightSchedules.of(controller));
        add(MenuName.FLIGHT_INFO, FlightInfo.of(controller), MenuDescription.flightInfo());
        add(MenuName.BOOKING, Booking.of(controller), MenuDescription.booking());
        add(MenuName.BOOKING_CANCEL, BookingCancel.of(controller), MenuDescription.bookingCancel());
        add(MenuName.BOOKING_VIEW, BookingView.of(controller), MenuDescription.bookingView());
        add(MenuName.LOGIN, Login.of(controller));
    }
}
