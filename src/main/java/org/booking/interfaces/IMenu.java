package org.booking.interfaces;

import org.booking.libs.Console;

public interface IMenu {
    int exitCode = -10;
    String sep1 = "=";
    String sep2 = "-";
    String logo = "BOOKING";
    int repeatSpaceCount = 100;

    default void displayLogo() {
        int logoSpaceCount = repeatSpaceCount / 2 - logo.length() / 2;

        Console.hide(String.format("%s\n", sep1.repeat(repeatSpaceCount)));
        Console.accept(String.format("%s %s\n", " ".repeat(logoSpaceCount), logo));
        Console.hide(String.format("%s\n", sep1.repeat(repeatSpaceCount)));
    }

    void run();
}
