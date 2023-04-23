package org.booking.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DateUtil {
    private long timestamp;
    private LocalDateTime localDateTime;
    private Date date;

    private DateUtil(long timestamp) {
        this.timestamp = timestamp;
        this.date = new Date(timestamp);
        localDateTime = Instant.ofEpochMilli(this.date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * Create date now
     *
     * @return DateUtil instance
     */
    public static DateUtil of() {
        return new DateUtil(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * Create date from LocalDateTime
     *
     * @param date date in LocalDateTime
     * @return DateUtil instance
     */
    public static DateUtil of(LocalDateTime date) {
        long ms = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return new DateUtil(ms);
    }

    /**
     * Create date from string by pattern
     *
     * @param string  data in string format
     * @param pattern string pattern for parse string format
     * @return DateUtil instance
     */
    public static DateUtil of(String string, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        long ts = LocalDate.parse(string, formatter)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        return new DateUtil(ts);
    }

    public static DateUtil of(String string) {
        return DateUtil.of(string, "dd/MM/yyyy");
    }

    /**
     * Create date from timestamp
     *
     * @param timestamp date in ms
     * @return DateUtil instance
     */
    public static DateUtil of(long timestamp) {
        return new DateUtil(timestamp);
    }

    public static DateUtil of(int year, int month, int day, int h, int m) {
        long timeStamp = LocalDateTime.of(year, month, day, h, m).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return DateUtil.of(timeStamp);
    }

    public static DateUtil of(int year, int month, int day) {
        return of(year, month, day, 0, 0);
    }

    @Override
    public int hashCode() {
        return new Date(this.timestamp).hashCode();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * @param pattern like yyyy-MM-dd HH:mm:ss
     * @return string from data formatted by pattern
     */
    public String formatter(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return this.localDateTime.format(formatter);
    }

    /**
     * @param formatStyle FULL/LONG/MEDIUM/SHORT
     * @return string from data formatted by FormatStyle
     */
    public String formatter(FormatStyle formatStyle) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(formatStyle);
        return localDateTime.format(formatter);
    }

    /**
     * Formatter with default pattern
     *
     * @return string from data formatted by yyyy-MM-dd HH:mm:ss
     */
    public String formatter() {
        return formatter("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * DateUtil instance.getYear().
     *
     * @return int value from = -999999999, to = 999999999
     */
    public int getYear() {
        return localDateTime.getYear();
    }

    /**
     * DateUtil instance.getMonth().
     *
     * @return int value from = 1, to = 12
     */
    public int getMonth() {
        return localDateTime.getMonth().getValue();
    }

    /**
     * DateUtil instance.getDayOfMonth().
     *
     * @return int value from = 1, to = 31
     */
    public int getDayOfMonth() {
        return localDateTime.getDayOfMonth();
    }

    /**
     * DateUtil instance.getHour().
     *
     * @return int value from = 0, to = 23
     */
    public int getHour() {
        return localDateTime.getHour();
    }

    /**
     * DateUtil instance.getMinute().
     *
     * @return int value from = 0, to = 59
     */
    public int getMinute() {
        return localDateTime.getMinute();
    }

    /**
     * DateUtil instance.getSecond().
     *
     * @return int value from = 0, to = 59
     */
    public int getSecond() {
        return localDateTime.getSecond();
    }

    /**
     * DateUtil instance.getSecond().
     *
     * @return long time millisecond from 1970.01.01 00:00
     */
    public long getMillis() {
        return timestamp;
    }

    private DateUtil withOverflow(LocalDateTime localDateTime) {
        long ms = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.date = new Date(ms);
        this.localDateTime = localDateTime;
        return DateUtil.of(ms);
    }

    private DateUtil withOverflow(long timestamp) {
        this.timestamp = timestamp;
        this.date = new Date(timestamp);
        localDateTime = Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return DateUtil.of(timestamp);
    }

    /**
     * DateUtil instance.plusDays
     *
     * @param days int param days. if value > 0 : plus : minus
     * @return DateUtil instance
     */
    public DateUtil plusDays(int days) {
        if (days == 0) return this;

        LocalDateTime time;
        if (days > 0) {
            time = this.localDateTime.plusDays(days);
        } else {
            time = this.localDateTime.minusDays(Math.abs(days));
        }

        return withOverflow(time);
    }

    /**
     * DateUtil instance.plusHours
     *
     * @param hours int param hours. if value > 0 : plus : minus
     * @return DateUtil instance
     */
    public DateUtil plusHours(int hours) {
        if (hours == 0) return this;

        LocalDateTime time;
        if (hours > 0) {
            time = this.localDateTime.plusHours(hours);
        } else {
            time = this.localDateTime.minusHours(Math.abs(hours));
        }

        return withOverflow(time);
    }

    /**
     * DateUtil instance.plusMinutes
     *
     * @param minutes int param  hours. if value > 0 : plus : minus
     * @return DateUtil instance
     */
    public DateUtil plusMinutes(int minutes) {
        if (minutes == 0) return this;

        LocalDateTime time;
        if (minutes > 0) {
            time = this.localDateTime.plusMinutes(minutes);
        } else {
            time = this.localDateTime.minusMinutes(Math.abs(minutes));
        }
        return withOverflow(time);
    }

    /**
     * Round time
     *
     * @param ms value to round
     * @return new DateUtil instance
     */
    public DateUtil round(long ms) {
        withOverflow(this.timestamp / ms * ms);
        return new DateUtil(this.timestamp / ms * ms);
    }

    /**
     * Round time to 15 min
     *
     * @return new DateUtil instance
     */
    public DateUtil round() {
        return round(30 * 60 * 1000);
    }

    /**
     * Round to current dat
     *
     * @return new DateUtil instance
     */
    public DateUtil roundDay() {
        return round(86400000);
    }

    /**
     * Get length of moth
     *
     * @param year  int year
     * @param month int month from 1 to 12
     * @return int length of this month
     */
    public static int lengthOfMonth(int year, int month) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

    /**
     * Get different between time in Duration
     *
     * @param start long time in ms
     * @param end   long time in ms
     * @return Duration
     */
    public static Duration betweenDuration(long start, long end) {
        return Duration.between(Instant.ofEpochMilli(start), Instant.ofEpochMilli(end));
    }

    /**
     * Get different between time in Period
     *
     * @param start long time in ms
     * @param end   long time in ms
     * @return Period
     */
    public static Period betweenPeriod(long start, long end) {
        LocalDate localStart = DateUtil.of(start).getLocalDateTime().toLocalDate();
        LocalDate localEnd = DateUtil.of(end).getLocalDateTime().toLocalDate();
        return Period.between(localStart, localEnd);
    }
}
