package org.booking.command;

public class BookingCancel extends Command {
    public BookingCancel() {
        super();
    }

    public static Command of() {
        return new BookingCancel();
    }

    @Override
    public void execute() {

    }
}
