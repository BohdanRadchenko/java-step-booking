package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Flight;
import org.booking.helpers.Constants;
import org.booking.utils.Console;

import java.util.List;

public class FlightSchedules extends Command {
    public FlightSchedules(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new FlightSchedules(controller);
    }

    private void displayFlight(int n, Flight f) {
        String res = String.format("| %-3d | %s", n, f.prettyFormat());

        if (n % 2 == 0) {
            Console.table1(res);
        } else {
            Console.table2(res);
        }
    }

    @Override
    public void execute() {
        List<Flight> flightNextDay = controller.flight.getFlightNextDay();
        if (flightNextDay.size() == 0) {
            String msg = String.format("%s\n", Constants.sep2.repeat(Constants.repeatSpaceCount));
            Console.warning(msg);
            return;
        }
        Console.table1(String.format("| %-3s | %s", "ID", Flight.prettyFormatHead()));
        for (int i = 0; i < flightNextDay.size(); i++) {
            displayFlight(i + 1, flightNextDay.get(i));
        }
    }
}
