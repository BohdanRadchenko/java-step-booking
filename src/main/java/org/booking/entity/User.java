package org.booking.entity;

// TODO: example class
public class User extends Entity {
    private final String login;
    private final String password;
    private final String name;
    private final String surname;

    public User(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname) {
        this(null, null, name, surname);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
