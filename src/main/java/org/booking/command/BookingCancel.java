package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Booking;
import org.booking.enums.Message;
import org.booking.helpers.Validation;
import org.booking.utils.Console;

public class BookingCancel extends Command {
    public BookingCancel(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new BookingCancel(controller);
    }

    private String enterBookingId() {
        Console.input(Message.BOOKING_ENTER_CODE);
        String str = Console.readString();
        if (Validation.bookingId(str)) {
            return str;
        }
        Console.error("Invalid booking id");
        return enterBookingId();
    }

    private void displayBooking(Booking booking) {
        if (booking == null) {
            Console.error("Booking not found!");
            return;
        }
        controller.booking.cancelBooking(booking);
    }

    @Override
    public void execute() {
        if (controller.booking.isEmpty()) {
            Console.warning("Nothing found!\n");
            return;
        }

        String bookingId = enterBookingId();

        Booking booking = controller.booking.getByCode(bookingId);

        displayBooking(booking);
    }
}
