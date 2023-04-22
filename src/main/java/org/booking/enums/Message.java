package org.booking.enums;

public enum Message {
    //MENU
    MENU_ENTER_NUMBER("Enter menu number"),
    MENU_NOT_EXIST("Menu does not exist"),

    // PARSE
    PARSE_INT_FROM_STRING("Cannot parse int from string"),

    // USER
    USER_ENTER_LOGIN("Enter login"),
    USER_ENTER_PASSWORD("Enter password"),
    USER_ENTER_CONFIRM_PASSWORD("Enter confirm password"),
    USER_ENTER_FIRST_NAME("Enter first name"),
    USER_ENTER_LAST_NAME("Enter last name"),

    // BOOKING,
    BOOKING_ENTER_CODE("Enter booking id"),

    //    PASSENGER
    PASSENGER_ENTER_FULL_NAME("Enter passenger full name");


    public final String message;

    Message(String msg) {
        this.message = msg;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
