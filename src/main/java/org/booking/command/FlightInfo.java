package org.booking.command;

import org.booking.controllers.Controller;

public class FlightInfo extends Command {
    public FlightInfo(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new FlightInfo(controller);
    }

    @Override
    public void execute() {
        /*
         * Пользователю предлагается ввести айди рейса.
         * Далее по этому рейсу выводится вся информация
         * - дата, время, место назначения, количество свободных мест.
         * После этого снова отображается главное меню.
         * */
    }
}
