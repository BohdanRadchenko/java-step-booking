package org.booking.utils;

import org.booking.enums.Message;

import java.util.Arrays;

public class Console extends Input implements ConsoleColors {
    private enum TableSize {
        FULL, FLIGHT_FULL, FLIGHT_INFO, BOOKING_FLIGHTS, AIRPORT
    }

    public static final TableSize FULL = TableSize.FULL;
    public static final TableSize FLIGHT_FULL = TableSize.FLIGHT_FULL;
    public static final TableSize FLIGHT_INFO = TableSize.FLIGHT_INFO;
    public static final TableSize BOOKING_FLIGHTS = TableSize.BOOKING_FLIGHTS;
    public static final TableSize AIRPORT = TableSize.AIRPORT;

    public static void print(String str) {
        System.out.print(str);
    }

    public static void reset() {
        print(RESET);
    }

    public static void ln() {
        System.out.println();
    }

    public static void title(String string) {
        print(CYAN_BOLD);
        print(string);
        reset();
    }

    public static void title(Message msg) {
        title(msg.message);
    }

    public static void msg(String string) {
        print(CYAN);
        print(string);
        reset();
    }

    public static void msg(Message msg) {
        msg(msg.message);
    }

    public static void hide(String msg) {
        print(WHITE);
        print(msg);
        reset();
    }

    public static void input(String string) {
        print(BLUE_UNDERLINED);
        print(string);
        print(":");
        reset();
        print(" ");
    }

    public static void input(Message msg) {
        input(msg.message);
    }

    private static String table(String string, TableSize size, int type) {
        String reduce = Arrays.stream(string.split("\n")).reduce(" ", String::concat);
        String color;
        if (type == 1) {
            color = "";
        } else {
            color = String.format("%s%s", BLACK, WHITE_BACKGROUND);
        }

        String msg = switch (size) {
            case FULL -> reduce;
            case FLIGHT_FULL -> String.format("%-133s", reduce);
            case FLIGHT_INFO -> String.format("%-100s%s", reduce, " ".repeat(2));
            case BOOKING_FLIGHTS -> String.format("%-100s", reduce);
            case AIRPORT -> String.format("%-45s", reduce);
        };

        String end = switch (size) {
            case FULL -> String.format("\n%s", RESET);
            case FLIGHT_FULL, FLIGHT_INFO, BOOKING_FLIGHTS, AIRPORT -> String.format("%s\n", RESET);
        };

        return String.format("%s%s%s", color, msg, end);
    }

    public static void table1(String string, TableSize size) {
        print(table(string, size, 1));
    }

    public static void table1(String string) {
        table1(string, TableSize.FULL);
    }


    public static void table2(String string, TableSize size) {
        print(table(string, size, 2));
    }

    public static void table2(String string) {
        table2(string, TableSize.FULL);
    }

    public static void success(String string) {
        print(GREEN);
        print(string);
        reset();
    }

    public static void success(Message msg) {
        success(msg.message);
    }

    public static void warning(String string) {
        print(YELLOW);
        print(string);
        reset();
        ln();
    }

    public static void warning(Message msg) {
        warning(msg.message);
    }

    public static void error(String string) {
        print(RED);
        print(string);
        reset();
        ln();
    }

    public static void error(Message msg) {
        error(msg.message);
    }


    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void caret() {
        Console.print(">>> ");
    }
}