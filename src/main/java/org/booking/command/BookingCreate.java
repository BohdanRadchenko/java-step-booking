package org.booking.command;

import org.booking.controllers.Controller;
import org.booking.entity.Airport;
import org.booking.entity.Booking;
import org.booking.entity.Flight;
import org.booking.entity.User;
import org.booking.enums.Message;
import org.booking.helpers.PrettyFormat;
import org.booking.helpers.Validation;
import org.booking.ui.menu.MenuStack;
import org.booking.utils.*;

import java.time.format.FormatStyle;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookingCreate extends Command {
    public BookingCreate(Controller controller) {
        super(controller);
    }

    public static Command of(Controller controller) {
        return new BookingCreate(controller);
    }

    private void displayAirports(List<Airport> airports) {
        Console.table1(PrettyFormat.airportHead(), Console.AIRPORT);
        IntStream
                .range(0, airports.size())
                .forEach(i -> {
                    String info = String.format("%s\n", PrettyFormat.airport(airports.get(i)));
                    if (i % 2 == 0) {
                        Console.table2(info, Console.AIRPORT);
                    } else {
                        Console.table1(info, Console.AIRPORT);
                    }
                });
    }

    private void displayAllAirports() {
        List<Airport> airports = Arrays.stream(Airport.values()).toList();
        displayAirports(airports);
    }

    private String readUserInput() {
        String str = Console.readString();

        if (Parser.parseIsExit(str)) {
            MenuStack.refresh();
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

        displayAirports(airports);

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
        if (Parser.parseIsHelp(str)) {
            displayAllAirports();
            Console.caret();
            return enterAirport();
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
            throw new RuntimeException("Invalid date format (e.q dd/MM/yyyy)");
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
        final int spreadYear = 100;
        final int minYears = 2000;
        try {
            year = Parser.parseInt(splits.get(2));
            if (year < spreadYear) {
                year += minYears;
            }
            if (year < currentYear) {
                throw new RuntimeException("Invalid year!");
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        try {
            month = Parser.parseInt(splits.get(1));
            if (year == currentYear && month < currentMonth) {
                throw new RuntimeException("Invalid month!");
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        try {
            day = Parser.parseInt(splits.get(0));
            int maxDay = DateUtil.lengthOfMonth(year, month);
            int minDay = year == currentYear && month == currentMonth ? currentDay : 1;
            if (day < minDay || day > maxDay) {
                throw new RuntimeException("Invalid day!");
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return DateUtil.of(year, month, day).getMillis();
    }

    private long enterDate() {
        String readString = Console.readString();
        if (Parser.parseIsBack(readString) || Parser.parseIsExit(readString)) {
            MenuStack.refresh();
            return 1L;
        }
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
        String readString = Console.readString();
        if (Parser.parseIsBack(readString) || Parser.parseIsExit(readString)) {
            MenuStack.refresh();
            return 0;
        }
        try {
            int n = Parser.parseInt(readString);
            if (n <= 0) {
                throw new RuntimeException("Invalid number!");
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

    private void displayFlights(int i, List<Flight> flights) {
        List<String> strings = new ArrayList<>();
        IntStream
                .range(0, flights.size())
                .forEach(idx -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (idx == 0) {
                        stringBuilder.append(String.format("| %-3d | ", i + 1));
                    } else {
                        stringBuilder.append(String.format("| %-3s | ", " "));
                    }
                    stringBuilder.append(PrettyFormat.flightFull(flights.get(idx)));
                    strings.add(new String(stringBuilder));
                });
        if (i % 2 == 0) {
            strings.forEach(idx -> Console.table2(idx, Console.FLIGHT_FULL));
        } else {
            strings.forEach(idx -> Console.table1(idx, Console.FLIGHT_FULL));
        }
    }

    private List<Flight> chooseFlight(List<List<Flight>> flights) {
        String readString = Console.readString();
        if (Parser.parseIsBack(readString) || Parser.parseIsExit(readString) || Parser.parseIsHelp(readString)) {
            MenuStack.refresh();
            return null;
        }
        int maxFlightsInOne = flights.stream().mapToInt(List::size).reduce(Integer::max).orElse(0);

        if (Validation.flightId(readString) && maxFlightsInOne == 1) {
            List<Flight> flightsOne = new ArrayList<>();
            flights.forEach(f -> flightsOne.add(f.get(0)));
            Optional<Flight> fl = flightsOne
                    .stream()
                    .filter(f -> StringWorker.toLowerCase(f.getCode()).equals(StringWorker.toLowerCase(readString)))
                    .findFirst();
            if (fl.isEmpty()) {
                Console.warning(String.format("Not found %s\n", readString));
                Console.caret();
                return chooseFlight(flights);
            }
            List<Flight> res = new ArrayList<>();
            res.add(fl.get());
            return res;
        }

        try {
            int idx = Parser.parseInt(readString);
            if (idx <= 0 || idx > flights.size()) {
                throw new RuntimeException(String.format("Invalid value %s\n", readString));
            }
            return flights.get(idx - 1);
        } catch (RuntimeException ex) {
            Console.warning(ex.getMessage());
            Console.caret();
            return chooseFlight(flights);
        }
    }

    private void displayFlights(List<List<Flight>> flights) {
        Console.table1(String.format("| %-3s | %s", "ID", PrettyFormat.flightFullHead()), Console.FLIGHT_FULL);
        IntStream.range(0, flights.size())
                .forEach(i -> {
                    displayFlights(i, flights.get(i));
                });
    }

    private boolean chooseNext() throws RuntimeException {
        String readString = Console.readString();
        try {
            if (!Parser.parseIsCode(readString)) {
                throw new RuntimeException("...?");
            }
            boolean isYes = Parser.parseIsYes(readString);
            boolean isNo = Parser.parseIsNo(readString)
                    || Parser.parseIsExit(readString)
                    || Parser.parseIsBack(readString);
            if (!isYes && !isNo) {
                throw new RuntimeException("Yes/No");
            }
            return isYes;
        } catch (RuntimeException ex) {
            Console.warning(ex.getMessage());
            Console.caret();
            return chooseNext();
        }
    }

    private boolean chooseContinue() {
        Console.input(Message.BOOKING_NEXT);
        return chooseNext();
    }

    private List<Flight> enterFlights(List<List<Flight>> flights) {
        boolean withoutTrans = flights.stream().mapToInt(List::size).reduce(Integer::max).orElse(0) == 1;
        Message msg = withoutTrans
                ? Message.BOOKING_CHOOSE_FLIGHT
                : Message.BOOKING_CHOOSE_FLIGHT_INDEX;
        Console.input(msg);
        return chooseFlight(flights);
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
        if (count == 1 && controller.user.isAuth()) {
            return new ArrayList<>(Collections.singletonList(controller.user.getUser()));
        }
        return IntStream
                .range(0, count)
                .mapToObj(this::enterPassenger)
                .collect(Collectors.toList());
    }

    @Override
    public void execute() {
        Airport from = enterAirportFrom();
        Console.success(String.format("%-4s -> %s;\n", "FROM", from));

        Airport to = enterAirportTo(from);
        Console.success(String.format("%-4s -> %s;\n", "TO", to));

        long time = enterDepartureDate();
        Console.success(String.format("%s\n", DateUtil.of(time).formatter(FormatStyle.FULL)));

        List<List<Flight>> flightsForBookingForOne = controller.flight.getFlightsForBooking(from, to, time, 1);

        if (flightsForBookingForOne.size() == 0) {
            Console.warning("Nothing found!");
            return;
        }

        int seats = enterSeats();

        List<List<Flight>> flightsForBooking = controller.flight.getFlightsForBooking(from, to, time, seats);

        if (flightsForBooking.size() == 0) {
            Console.warning("Nothing found!");
            return;
        }


        displayFlights(flightsForBooking);

        boolean isNext = chooseContinue();

        if (!isNext) {
            Console.hide("Back to main menu");
            return;
        }

        List<Flight> flights = enterFlights(flightsForBooking);

        if (flights == null) {
            Console.error(Message.UE);
            return;
        }

        List<User> passengers = enterPassengers(seats);

        passengers.forEach(p -> {
            flights.forEach(f -> {
                Booking booking = controller.booking.createBooking(f, passengers.get(0), p);
                if (booking != null) {
                    f.addPassenger(p);
                    p.addBooking(booking);
                }
            });
        });

        Console.success(Message.BOOKING_SUCCESS);
    }
}
