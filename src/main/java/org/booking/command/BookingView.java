package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Booking;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.utils.Console;

import java.util.List;

public class BookingView extends Command {
    public BookingView(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new BookingView(controller);
    }

    private String enterFullName() {
        Console.input(Message.PASSENGER_ENTER_FULL_NAME);
        return Console.readString();
    }

    private User getUserByFullName() {
        String fullName = enterFullName();
        return controller.user.getUserByFullName(fullName);
    }

    private void displayBooking(Booking booking) {
        Console.log(booking.getId());
    }

    private void displayBookings(List<Booking> bookingByPassenger, List<Booking> bookingByCreator) {
        if (bookingByPassenger.size() != 0) {
            bookingByPassenger.forEach(this::displayBooking);
        }
        if (bookingByCreator.size() != 0) {
            bookingByCreator
                    .stream()
                    .filter(b -> !b.getCreatorId().equals(b.getPassengerId()))
                    .forEach(this::displayBooking);
        }
    }

    @Override
    public void execute() {
        User user;
        if (controller.user.isAuth()) {
            user = controller.user.getUser();
        } else {
            user = getUserByFullName();
        }
        if (user == null) return;
        List<Booking> bookingListByPassenger = controller.booking.getBookingsByPassengerId(user.getId());
        List<Booking> bookingListByCreator = controller.booking.getBookingsByCreatorId(user.getId());
        displayBookings(bookingListByPassenger, bookingListByCreator);
        /*
         * Пользователю предлагается ввести фамилию и имя.
         * После этого на экран выводится список всех бронирований,
         * которые были сделаны данным пользователем или в которых он является пассажиром.
         * */
    }
}
