package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Flight;
import org.booking.enums.Message;
import org.booking.helpers.Validation;
import org.booking.utils.Console;

public class FlightInfo extends Command {
    public FlightInfo(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new FlightInfo(controller);
    }

    private String enterFlightId() {
        Console.input(Message.FLIGHT_ENTER_CODE);
        String readString = Console.readString();
        boolean b = Validation.flightId(readString);

        if (!b) {
            Console.error("Invalid flight id!");
            return enterFlightId();
        }
        return readString;
    }

    private void displayFlight(Flight flight) {
        if (flight == null) {
            Console.warning("Nothing found!");
            return;
        }
        Console.title(flight.toString());
    }


    @Override
    public void execute() {
        String flightId = enterFlightId();
        Flight flight = controller.flight.getByFlightId(flightId);
        displayFlight(flight);
        
        /*
         * Пользователю предлагается ввести айди рейса.
         * Далее по этому рейсу выводится вся информация
         * - дата, время, место назначения, количество свободных мест.
         * После этого снова отображается главное меню.
         * */
    }
}
