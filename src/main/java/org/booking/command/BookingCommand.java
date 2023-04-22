package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Airport;
import org.booking.entity.Booking;
import org.booking.entity.Flight;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.helpers.Constants;
import org.booking.helpers.Validation;
import org.booking.ui.menu.Menu;
import org.booking.ui.menu.MenuStack;
import org.booking.utils.*;

import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookingCommand extends Command {
    public BookingCommand(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new BookingCommand(controller);
    }

    private void displayAirport(int n, Airport a) {
        String info = String.format("%d %s\n", n, a.prettyInfo());
        if (n % 2 == 0) {
            Console.table1(info);
            return;
        }
        Console.table2(info);
    }

    private void displayAllAirports() {
        IntStream
                .range(0, Airport.values().length)
                .forEach(i -> {
                    String info = String.format("%s\n", Airport.values()[i].prettyInfo());
                    if (i % 2 == 0) {
                        Console.table1(info);
                    } else {
                        Console.table2(info);
                    }
                });
    }

    private String readUserInput() {
        String str = Console.readString();

        if (Parser.parseIsExit(str)) {
            MenuStack.exit();
            return null;
        }

        if (Parser.parseIsBack(str)) {
            MenuStack.refresh();
            return null;
        }

        return StringWorker.toLowerCase(str);
    }

    private Airport airportSelector(String string) throws RuntimeException {
        List<Airport> airports = Arrays.stream(Airport.values()).filter(a -> a.equals(string)).toList();
        if (airports.size() == 0) {
            throw new RuntimeException("Nothing found!");
        }
        if (airports.size() == 1) {
            return airports.get(0);
        }

        IntStream
                .range(0, airports.size())
                .forEach(i -> displayAirport(i + 1, airports.get(i)));

        Console.input(Message.BOOKING_CHOOSE_AIRPORT);
        String str = readUserInput();

        try {
            int i = Parser.parseInt(str);
            if (i <= 0 || i > airports.size()) {
                throw new RuntimeException("Wrong airport index");
            }
            return airports.get(i - 1);
        } catch (RuntimeException ex) {
            Console.error(ex.getMessage());
            return airportSelector(string);
        }
    }

    private Airport enterAirport() {
        String str = readUserInput();
        try {
            int code = Parser.parseInt(str);
            if (code == Constants.helpCode) {
                displayAllAirports();
                Console.caret();
                return enterAirport();
            }
        } catch (NumberFormatException ignored) {
            Console.print("");
        }

        try {
            Airport ap = airportSelector(str);
            Logger.input(String.format("User enter %s airport", ap.code));
            return ap;
        } catch (RuntimeException ex) {
            Logger.error(ex.getMessage());
            Console.error(Message.AIRPORT_WRONG_INSERT_DATA);
            return enterAirportFrom();
        }
    }

    private Airport enterAirportFrom() {
        Message msg = Message.BOOKING_ENTER_AIRPORT_DEPARTURE;
        Logger.out(msg.message);
        Console.input(msg);
        return enterAirport();
    }

    private Airport enterAirportTo(Airport from) {
        Message msg = Message.BOOKING_ENTER_AIRPORT_ARRIVAL;
        Logger.out(msg.message);
        Console.input(msg);
        Airport ap = enterAirport();
        if (ap.equals(from) || ap.utm.getUtm() == from.utm.getUtm()) {
            Console.error(Message.UE);
            return enterAirportTo(from);
        }
        return ap;
    }

    private long parseDate(String date) throws RuntimeException {
        if (!Validation.bookingDate(date)) {
            throw new RuntimeException("Invalid date format");
        }
        List<String> splits = Arrays.stream(date
                        .replace("/", " ")
                        .replace(".", " ")
                        .replace("-", " ")
                        .split(" "))
                .map(String::trim)
                .toList();
        DateUtil now = DateUtil.of();
        int currentYear = now.getYear();
        int currentMonth = now.getMonth();
        int currentDay = now.getDayOfMonth();
        int year;
        int month;
        int day;
        try {
            year = Parser.parseInt(splits.get(2));
            if (year < currentYear) {
                throw new RuntimeException("Invalid year");
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        try {
            month = Parser.parseInt(splits.get(1));
            if (year == currentYear && month < currentMonth) {
                throw new RuntimeException("Invalid month");
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        try {
            day = Parser.parseInt(splits.get(0));
            int maxDay = DateUtil.lengthOfMonth(year, month);
            int minDay = year == currentYear && month == currentMonth ? currentDay : 1;
            if (day < minDay || day > maxDay) {
                throw new RuntimeException("Invalid day");
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return DateUtil.of(year, month, day).getMillis();
    }

    private long enterDate() {
        String readString = Console.readString();
        try {
            return parseDate(readString);
        } catch (RuntimeException ex) {
            Logger.error(ex.getMessage());
            Console.error(ex.getMessage());
            Console.caret();
            return enterDate();
        }

    }

    private long enterDepartureDate() {
        Message msg = Message.BOOKING_ENTER_DATE;
        Logger.out(msg.message);
        Console.input(msg);
        return enterDate();
    }

    private int enterSeatsInt() {
        try {
            int n = Console.readInt();
            if (n <= 0) {
                throw new RuntimeException("Invalid number");
            }
            return n;
        } catch (RuntimeException ex) {
            Logger.error(ex.getMessage());
            Console.error(ex.getMessage());
            return enterSeatsInt();
        }
    }

    private int enterSeats() {
        Message msg = Message.BOOKING_ENTER_SEATS;
        Logger.out(msg.message);
        Console.input(msg);
        return enterSeatsInt();
    }

    private Flight chooseFlight(List<Flight> flight) {
        String readString = Console.readString();
        if (Parser.parseIsBack(readString)) {
            MenuStack.refresh();
            return null;
        }
        if (Parser.parseIsExit(readString)) {
            MenuStack.exit();
            return null;
        }
        if (Validation.flightId(readString)) {
            Optional<Flight> fl = flight
                    .stream()
                    .filter(f -> StringWorker.toLowerCase(f.getCode()).equals(StringWorker.toLowerCase(readString)))
                    .findFirst();
            if (fl.isEmpty()) {
                Console.warning(String.format("Not found %s\n", readString));
                Console.caret();
                return chooseFlight(flight);
            }
            return fl.get();
        }

        try {
            int idx = Parser.parseInt(readString);
            if (idx <= 0 || idx > flight.size()) {
                throw new RuntimeException(String.format("Invalid value %s\n", readString));
            }
            return flight.get(idx - 1);
        } catch (RuntimeException ex) {
            Console.warning(ex.getMessage());
            Console.caret();
            return chooseFlight(flight);
        }
    }

    private Flight enterFlight(List<Flight> flight) {
        Console.table1(String.format("| %-3s | %s", "ID", Flight.prettyFormatHead()), true);
        IntStream
                .range(0, flight.size())
                .forEach(i -> {
                    String info = String.format("|%-3d | %s\n", i + 1, flight.get(i).prettyFormatFull());
                    if (i % 2 == 0) {
                        Console.table2(info, true);
                    } else {
                        Console.table1(info, true);
                    }
                });
        Console.input(Message.BOOKING_CHOOSE_FLIGHT);
        return chooseFlight(flight);
    }

    private User enterPassenger(int i) {
        if (i == 0 && controller.user.isAuth()) {
            return controller.user.getUser();
        }
        Console.input(String.format("%s #%d", Message.BOOKING_ENTER_PASSENGER_FIRST, i + 1));
        String first = Console.readString();
        Console.input(String.format("%s #%d", Message.BOOKING_ENTER_PASSENGER_LAST, i + 1));
        String last = Console.readString();
        return controller.user.getOrAddByFull(first, last);
    }

    private List<User> enterPassengers(int count) {
        return IntStream
                .range(0, count)
                .mapToObj(this::enterPassenger)
                .collect(Collectors.toList());
    }

    @Override
    public void execute() {
        Airport from = enterAirportFrom();
        Console.accept(String.format("%-4s -> %s;\n", "FROM", from));

        Airport to = enterAirportTo(from);
        Console.accept(String.format("%-4s -> %s;\n", "TO", to));

        long time = enterDepartureDate();
        Console.accept(String.format("%s\n", DateUtil.of(time).formatter(FormatStyle.FULL)));

        int seats = enterSeats();

        List<Flight> flightsForBooking = controller.flight.getFlightsForBooking(from, to, time, seats);

        if (flightsForBooking.size() == 0) {
            Console.warning("Nothing found!");
            return;
        }

        Flight flight = enterFlight(flightsForBooking);

        List<User> passengers = enterPassengers(seats);

        passengers.forEach(p -> {
            Booking booking = controller.booking.createBooking(flight, passengers.get(0), p);
            if (booking == null) return;
            flight.addPassenger(p);
            p.addBooking(booking);
        });


        /*
         * Пользователю предлагается последовательно ввести следующую информацию:
         * место назначения, дата, количество человек (сколько необходимо купить билетов).
         * После этого программа должна выполнить поиск рейсов, которые удовлетворяют заданным условиям
         * (на рейсе свободных мест должно быть не меньше, чем количество указанных пассажиров).
         * Все найденные рейсы должны быть выведены на экран.
         * После этого пользователь может выбрать один из найденных рейсов,
         * указав его порядковый номер, либо вернуться в главное меню (выбрав пункт 0).
         * Если пользователь решает забронировать рейс,
         * ему необходимо ввести данные (имя и фамилия) для того количества пассажиров,
         * которое было указано при поиске.
         * */
    }
}
