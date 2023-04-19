package org.booking.interfaces;

public interface IMenu {
    int exitCode = -10;
    int helpCode = -20;
    String separator = "=";
    String logo = "BOOKING";

    default void displayLogo() {
        // TODO: 18.04.2023 System.out to Console method;
        int repeatSpaceCount = 100;
        int logoSpaceCount = repeatSpaceCount / 2 - logo.length() / 2;

        System.out.printf("%s\n", separator.repeat(repeatSpaceCount));
        System.out.printf("%s %s\n", " ".repeat(logoSpaceCount), logo);
        System.out.printf("%s\n", separator.repeat(repeatSpaceCount));
    }

    void run();
}
