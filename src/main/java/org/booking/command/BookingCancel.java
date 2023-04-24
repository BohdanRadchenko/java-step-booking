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

    private void display(Booking booking) {
        Console.table1(PrettyFormat.flightFullHead(), Console.FLIGHT_INFO);
        Console.table2(PrettyFormat.flightFull(booking.getFlight()), Console.FLIGHT_INFO);
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
            Console.warning("Booking not found!");
            return;
        }

        display(booking);

        boolean status = controller.booking.cancelBooking(booking);

        if (!status) {
            Console.error(Message.UE);
            return;
        }

        Console.success(String.format("Booking %s cancelled", booking.getCode()));
    }
}
