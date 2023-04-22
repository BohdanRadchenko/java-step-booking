package org.booking.ui.menu;

import org.booking.command.*;
import org.booking.controllers.Controller;
import org.booking.enums.MenuName;
import org.booking.helpers.MenuDescription;

public class MainMenu extends Menu {
    public MainMenu(Controller controller) {
        super("Main menu", controller);
    }

    @Override
    protected void set() {
        boolean isAuth = controller.user.isAuth();
        MenuName bookingTitle = isAuth
                ? MenuName.BOOKING_VIEW_LOGIN
                : MenuName.BOOKING_VIEW;
        String bookingsViewDesc = isAuth ? null : MenuDescription.bookingView();
        add(MenuName.FLIGHT_SCHEDULES, FlightSchedules.of(controller));
        add(MenuName.FLIGHT_INFO, FlightInfo.of(controller), MenuDescription.flightInfo());
        add(MenuName.BOOKING, BookingCommand.of(controller), MenuDescription.booking());
        add(bookingTitle, BookingView.of(controller), bookingsViewDesc);
        add(MenuName.BOOKING_CANCEL, BookingCancel.of(controller), MenuDescription.bookingCancel());
        if (isAuth) {
            add(MenuName.LOGOUT, AuthLogout.of(controller), false);
        }
    }
}
