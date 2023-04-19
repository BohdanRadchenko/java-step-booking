package org.booking.command;

public class BookingView extends Command {
    public BookingView() {
        super();
    }

    public static Command of() {
        return new BookingView();
    }

    @Override
    public void execute() {

    }
}
