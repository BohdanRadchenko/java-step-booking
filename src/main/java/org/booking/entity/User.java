package org.booking.entity;

import org.booking.utils.StringWorker;

// TODO: example class
public class User extends Entity {
    private final String login;
    private final String password;
    private final String firstName;
    private final String lastName;

    public User(String login, String password, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String login, String password) {
        this(login, password, "First name", "Last name");
    }

    public String getFullName() {
        return String.format("%s %s", StringWorker.toCapitalize(firstName), StringWorker.toCapitalize(lastName));
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
