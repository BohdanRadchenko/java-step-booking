package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Flight;
import org.booking.helpers.Constants;
import org.booking.helpers.PrettyFormat;
import org.booking.utils.Console;

import java.util.List;
import java.util.stream.IntStream;

public class FlightSchedules extends Command {
    public FlightSchedules(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new FlightSchedules(controller);
    }

    private void displayFlight(int n, Flight f) {
        String res = String.format("| %-3d | %s", n, PrettyFormat.flightFull(f));

        if (n % 2 == 0) {
            Console.table2(res, Console.FLIGHT_FULL);
        } else {
            Console.table1(res, Console.FLIGHT_FULL);
        }
    }

    private void displayFlights(List<Flight> flights) {
        String head = String.format("| %-3s | %s", "ID", PrettyFormat.flightFullHead());
        Console.table1(head, Console.FLIGHT_FULL);
        IntStream.range(0, flights.size()).forEach(i -> {
            displayFlight(i + 1, flights.get(i));
        });
    }

    @Override
    public void execute() {
        List<Flight> flightNextDay = controller.flight.getFlightNextDay();
        if (flightNextDay.size() == 0) {
            String msg = String.format("%s\n", Constants.SEP_2.repeat(Constants.REPEAT_SPACE_COUNT));
            Console.warning(msg);
            return;
        }
        displayFlights(flightNextDay);
    }
}
