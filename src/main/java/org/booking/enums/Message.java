package org.booking.enums;

public enum Message {
    UE("Unknown error"),
    //MENU,
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

    // AIRPORT
    AIRPORT_EG("(e.g 'BOS', 'USA', 'Boston')"),
    AIRPORT_EG_2("(e.g 'LHR', 'United Kingdom', 'UK','London')"),
    AIRPORT_EG_3("(e.g 'KBP', 'Ukraine', 'UA', 'Kyiv')"),
    AIRPORT_WRONG_INSERT_DATA(String.format("Wrong airport data %s", AIRPORT_EG)),

    // BOOKING
    BOOKING_ENTER_CODE("Enter booking id"),
    BOOKING_ENTER_AIRPORT_DEPARTURE(String.format("Enter departure airport %s", AIRPORT_EG_3)),
    BOOKING_ENTER_AIRPORT_ARRIVAL(String.format("Enter airport of arrival %s", AIRPORT_EG_2)),
    BOOKING_CHOOSE_AIRPORT("Choose your airport index"),
    BOOKING_ENTER_DATE("Enter data"),
    BOOKING_ENTER_SEATS("Enter the number of passengers"),
    BOOKING_CHOOSE_FLIGHT("Choose flight (ID or CODE)"),
    BOOKING_CHOOSE_FLIGHT_INDEX("Enter flight ID"),
    BOOKING_ENTER_PASSENGER_FIRST("Enter passenger first name"),
    BOOKING_ENTER_PASSENGER_LAST("Enter passenger last name"),
    BOOKING_NEXT("Continue (y/n) ?"),
    BOOKING_SUCCESS("SUCCESS!"),

    // FLIGHT
    FLIGHT_ENTER_CODE("Enter flight id"),

    //    PASSENGER
    PASSENGER_ENTER_FULL_NAME("Enter passenger first name and last name");


    public final String message;

    Message(String msg) {
        this.message = msg;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
