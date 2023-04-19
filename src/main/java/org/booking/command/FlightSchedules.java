package org.booking.command;

public class FlightSchedules extends Command {
    public FlightSchedules() {
        super();
    }

    public static Command of() {
        return new FlightSchedules();
    }

    @Override
    public void execute() {
        /*
         * Поaказывает информацию про все рейсы из Киева в ближайшие 24 часа. После этого снова отображается главное меню.
         */
    }
}
