package org.booking.command;

public class BookingView extends Command {
    public BookingView() {
        super();
    }

    public static Command of() {
        return new BookingView();
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
