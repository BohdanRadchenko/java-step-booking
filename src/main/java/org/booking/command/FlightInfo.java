package org.booking.command;

public class FlightInfo extends Command {
    public FlightInfo() {
        super();
    }

    public static Command of() {
        return new FlightInfo();
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
