package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Booking;
import org.booking.enums.Message;
import org.booking.helpers.PrettyFormat;
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

        Console.error("Invalid booking code");
        return enterBookingId();
    }

    @Override
    public void execute() {
        if (controller.booking.isEmpty()) {
            Console.warning("Nothing found!\n");
            return;
        }

        String bookingId = enterBookingId();

        Booking booking = controller.booking.getByCode(bookingId);

        if (booking == null) {
            Console.error("Booking not found!");
            return;
        }
        Console.table1(PrettyFormat.flightFull(booking.getFlight()));
        controller.booking.cancelBooking(booking);
        Console.accept(String.format("Booking %s cancelled", booking.getCode()));
    }
}
