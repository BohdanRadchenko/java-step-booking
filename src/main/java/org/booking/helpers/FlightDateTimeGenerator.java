package org.booking.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

public class FlightDateTimeGenerator {
    private static final int MINUTE_IN_MILLISECONDS = 60 * 1000;
    private static final int QUARTER_HOUR_IN_MILLISECONDS = 15 * MINUTE_IN_MILLISECONDS;

    private static final int MIN_YEAR = 2022;
    private static final int MAX_YEAR = 2023;
    private static final int MIN_MONTH = 0;
    private static final int MAX_MONTH = 11;
    private static final int MIN_DAY_OF_MONTH = 1;
    private static final int MAX_DAY_OF_MONTH = 31;
    private static final int MIN_HOUR_OF_DAY = 0;
    private static final int MAX_HOUR_OF_DAY = 23;
    private static final int MIN_MINUTE_OF_HOUR = 0;
    private static final int MAX_MINUTE_OF_HOUR = 59;

    private static final Random random = new Random();

    public static long generateRandomFlightDateTimeInMillis() {
        int year = MIN_YEAR + random.nextInt(MAX_YEAR - MIN_YEAR + 1);
        int month = MIN_MONTH + random.nextInt(MAX_MONTH - MIN_MONTH + 1);
        int dayOfMonth = MIN_DAY_OF_MONTH + random.nextInt(MAX_DAY_OF_MONTH - MIN_DAY_OF_MONTH + 1);
        int hourOfDay = MIN_HOUR_OF_DAY + random.nextInt(MAX_HOUR_OF_DAY - MIN_HOUR_OF_DAY + 1);
        int minuteOfHour = (random.nextInt(4) * 15) + MIN_MINUTE_OF_HOUR; // 0, 15, 30 or 45
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minuteOfHour);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar.getTimeInMillis();
    }

    public static String getDateFromMillis(long millis) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(calendar.getTime());
    }

}