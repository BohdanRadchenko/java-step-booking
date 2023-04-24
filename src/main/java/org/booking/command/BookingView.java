package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Booking;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.helpers.PrettyFormat;
import org.booking.utils.Console;

import java.util.List;
import java.util.stream.IntStream;

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
        String info = PrettyFormat.booking(booking);
        if (i % 2 == 0) {
            Console.table2(info, Console.BOOKING_FLIGHTS);
        } else {
            Console.table1(info, Console.BOOKING_FLIGHTS);
        }
    }

    private void displayBookings(List<Booking> bookings) {
        Console.table1(PrettyFormat.bookingHead(), Console.BOOKING_FLIGHTS);
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
        if (user == null) {
            Console.warning("User not found!");
            return;
        }
        List<Booking> bookings = controller.booking.getBookingsByUserId(user.getId());
        displayBookings(bookings);
    }
}
