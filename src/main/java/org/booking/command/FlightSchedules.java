package org.booking.command;

import org.booking.controllers.Controller;

public class FlightSchedules extends Command {
    public FlightSchedules(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new FlightSchedules(controller);
    }

    @Override
    public void execute() {
        /*
         * Поaказывает информацию про все рейсы из Киева в ближайшие 24 часа. После этого снова отображается главное меню.
         */
    }
}
