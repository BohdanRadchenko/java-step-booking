package org.booking.command;

public class BookingCancel extends Command {
    public BookingCancel() {
        super();
    }

    public static Command of() {
        return new BookingCancel();
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
