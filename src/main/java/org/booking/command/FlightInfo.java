package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Flight;
import org.booking.enums.Message;
import org.booking.helpers.PrettyFormat;
import org.booking.helpers.Validation;
import org.booking.utils.Console;

public class FlightInfo extends Command {
    private int REFRESH_COUNTER = 0;
    private final int MAX_REFRESH_COUNTER = 3;

    public FlightInfo(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new FlightInfo(controller);
    }

    private String enterFlightId() {
        REFRESH_COUNTER++;
        Console.input(Message.FLIGHT_ENTER_CODE);
        String readString = Console.readString();
        boolean b = Validation.flightId(readString);

        if (!b) {
            Console.error("Invalid flight id!");
            if (REFRESH_COUNTER <= MAX_REFRESH_COUNTER) {
                return enterFlightId();
            }
        }
        return readString;
    }

    private void displayFlight(Flight flight) {
        if (flight == null) {
            Console.warning("Nothing found!");
            return;
        }
        Console.table1(PrettyFormat.flightFullHead(), Console.FLIGHT_INFO);
        Console.table2(flight.toString(), Console.FLIGHT_INFO);
    }

    @Override
    public void execute() {
        String flightId = enterFlightId();
        Flight flight = controller.flight.getByFlightId(flightId);
        displayFlight(flight);
    }
}
