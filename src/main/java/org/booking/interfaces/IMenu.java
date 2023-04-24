package org.booking.interfaces;

import org.booking.utils.Console;
import org.booking.helpers.Constants;

import java.util.Arrays;

public interface IMenu {

    default void showSeparator() {
        Console.hide(String.format("%s\n", Constants.SEP_1.repeat(Constants.REPEAT_SPACE_COUNT)));
    }

    default void showSubSeparator() {
        Console.hide(String.format("\n%s\n\n", Constants.SEP_2.repeat(Constants.REPEAT_SPACE_COUNT)));
    }

    default void displayTitle(String title) {
        int titleSpaceCount = Constants.calcSpaceCount(title);

        showSeparator();
        Console.success(String.format("%s %s\n", " ".repeat(titleSpaceCount), title));
        showSeparator();
    }

    default void displayDescription(String description) {
        int min = Arrays.stream(description.split("\n"))
                .mapToInt(Constants::calcSpaceCount)
                .min()
                .orElse(0);
        Arrays.stream(description.split("\n"))
                .forEach(s -> {
                    Console.hide(String.format("%s%s\n", " ".repeat(min), s));
                });
        showSeparator();
    }

    default void displayLogo() {
        displayTitle(Constants.LOGO);
        Console.ln();
    }

    void run();
}
