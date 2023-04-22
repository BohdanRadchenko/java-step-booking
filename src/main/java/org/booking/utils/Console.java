package org.booking.utils;

import org.booking.enums.Message;

import java.util.Arrays;
import java.util.Collection;

public class Console extends Input implements ConsoleColors {

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

    public static void table1(String string) {
        String reduce = Arrays.stream(string.split("\n")).reduce("", String::concat);
        print(String.format("%s%s", WHITE, BLACK_BACKGROUND));
        print(String.format("%-110s%s", reduce, RESET));
        ln();
    }

    public static void table2(String string) {
        String reduce = Arrays.stream(string.split("\n")).reduce("", String::concat);
        print(String.format("%s%s", BLACK, WHITE_BACKGROUND));
        print(String.format("%-110s%s", reduce, RESET));
        ln();
    }


    public static void accept(String string) {
        print(GREEN);
        print(string);
        reset();
    }

    public static void accept(Message msg) {
        accept(msg.message);
    }

    public static void warning(String string) {
        print(YELLOW);
        print(string);
        reset();
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
}