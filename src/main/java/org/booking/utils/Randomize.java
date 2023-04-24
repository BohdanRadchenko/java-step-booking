package org.booking.utils;

import java.util.Random;

public class Randomize {

    private static final Random RANDOM_INSTANCE = new Random();

    public static int num(int min, int max) {
        return RANDOM_INSTANCE.nextInt(min, max);
    }

    public static int num(int max) {
        return num(0, max);
    }

    public static int num(int min, int max, int without) {
        int n = num(min, max);
        if (n == without) {
            return num(min, max, without);
        }
        return n;
    }
}
