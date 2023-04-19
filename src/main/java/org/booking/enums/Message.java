package org.booking.enums;

public enum Message {
    //MENU
    MENU_ENTER_NUMBER("Enter menu number"),
    MENU_NOT_EXIST("Menu does not exist"),

    // PARSE
    PARSE_INT_FROM_STRING("Cannot parse int from string");

    public final String message;

    Message(String msg) {
        this.message = String.format("%s:", msg);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
