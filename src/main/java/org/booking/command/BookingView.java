package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Booking;
import org.booking.entity.Flight;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.utils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BookingView extends Command {
    public BookingView(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new BookingView(controller);
    }

    private String enterFullName() {
        Console.input(Message.PASSENGER_ENTER_FULL_NAME);
        return Console.readString();
    }

    private User getUserByFullName() {
        String fullName = enterFullName();
        return controller.user.getUserByFullName(fullName);
    }

    private void displayBooking(int i, Booking booking) {
        List<String> stringList = Arrays.stream(booking.toString().split("\n")).toList();
        if (i % 2 == 0) {
            stringList.forEach(Console::table2);
        } else {
            stringList.forEach(Console::table1);
        }
    }

    private void displayBookings(List<Booking> bookings) {
        Console.table1(Flight.prettyFormatShortHead());
        IntStream
                .range(0, bookings.size())
                .forEach(i -> displayBooking(i, bookings.get(i)));
    }


    @Override
    public void execute() {
        User user;
        if (controller.user.isAuth()) {
            user = controller.user.getUser();
        } else {
            user = getUserByFullName();
        }
        if (user == null) return;
        List<Booking> bookings = controller.booking.getBookingsByUserId(user.getId());
        displayBookings(bookings);
    }
}
