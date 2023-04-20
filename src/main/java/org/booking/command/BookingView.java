package org.booking.command;

import org.booking.controllers.Controller;

public class BookingView extends Command {
    public BookingView(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new BookingView(controller);
    }

    @Override
    public void execute() {
        /*
         * Пользователю предлагается ввести фамилию и имя.
         * После этого на экран выводится список всех бронирований,
         * которые были сделаны данным пользователем или в которых он является пассажиром.
         * */
    }
}
