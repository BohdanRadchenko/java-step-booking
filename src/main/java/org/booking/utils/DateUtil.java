package org.booking.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DateUtil {
    private final long timestamp;
    private final LocalDateTime localDateTime;
    private final Date date;

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

    @Override
    public int hashCode() {
        return new Date(this.timestamp).hashCode();
    }

    /**
     * @param pattern like yyyy-MM-dd HH:mm:ss
     * @return string from data formatted by pattern
     */
    public String formatter(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * @param formatStyle FULL/LONG/MEDIUM/SHORT
     * @return string from data formatted by FormatStyle
     */
    public String formatter(FormatStyle formatStyle) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(formatStyle);
        return localDateTime.format(formatter);
    }
}
