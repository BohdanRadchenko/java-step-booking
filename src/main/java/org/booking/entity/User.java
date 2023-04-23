package org.booking.entity;

import org.booking.utils.StringWorker;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class User extends Entity {
    private final String login;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String fullName;

    private final Set<Booking> bookings = new HashSet<>();

    public User(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = createFullName();
    }

    public User(String firstName, String lastName) {
        this(null, null, firstName, lastName);
    }

    private String createFullName() {
        String first = Arrays
                .stream(firstName.split(" "))
                .map(StringWorker::toCapitalize)
                .collect(Collectors.joining(" "));
        String last = Arrays
                .stream(lastName.split(" "))
                .map(StringWorker::toCapitalize)
                .collect(Collectors.joining(" "));
        return String.format("%s %s", first, last);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean addBooking(Booking booking) {
        return bookings.add(booking);
    }

    public boolean cancelBooking(Booking booking) {
        return bookings.remove(booking);
    }
}
