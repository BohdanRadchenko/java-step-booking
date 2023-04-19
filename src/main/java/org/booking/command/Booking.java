package org.booking.command;

public class Booking extends Command {
    public Booking() {
        super();
    }

    public static Command of() {
        return new Booking();
    }

    @Override
    public void execute() {

    }
}
