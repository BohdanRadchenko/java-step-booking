package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Flight;
import org.booking.enums.Message;
import org.booking.helpers.PrettyFormat;
import org.booking.helpers.Validation;
import org.booking.utils.Console;

public class FlightInfo extends Command {
    private int refreshCounter = 0;
    private int maxRefresh = 3;

    public FlightInfo(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new FlightInfo(controller);
    }

    private String enterFlightId() {
        refreshCounter++;
        Console.input(Message.FLIGHT_ENTER_CODE);
        String readString = Console.readString();
        boolean b = Validation.flightId(readString);

        if (!b) {
            Console.error("Invalid flight id!");
            if (refreshCounter <= maxRefresh) {
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
        Console.table1(PrettyFormat.flightFullHead());
        Console.table2(flight.toString());
    }

    @Override
    public void execute() {
        String flightId = enterFlightId();
        Flight flight = controller.flight.getByFlightId(flightId);
        displayFlight(flight);
    }
}
