package org.booking.command;

import org.booking.controllers.Controller;

public class BookingCancel extends Command {
    public BookingCancel(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new BookingCancel(controller);
    }

    @Override
    public void execute() {
        /*
         * Пользователю предлагается ввести айди бронирования.
         * Если такое бронирование было найдено, оно отменяется.
         * После этого снова отображается главное меню.
         * Пользователь может отменить любое бронирование.
         * */
    }
}
