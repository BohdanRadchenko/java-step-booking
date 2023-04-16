package org.booking.libs;

import java.time.*;
import java.time.format.*;
import java.util.Date;

public class DateUtil {
    private final long timestamp;
    private final LocalDateTime localDateTime;
    private final Date date;

    private DateUtil(long timestamp) {
        this.timestamp = timestamp;
        this.date = new Date(timestamp);
        this.localDateTime = Instant.ofEpochMilli(this.date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


    /**
     * DateUtil.of() create DateUtil instance of current time
     *
     * @return DateUtil
     */
    public static DateUtil of() {
        return new DateUtil(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * DateUtil.of() create DateUtil instance from string by format pattern
     *
     * @param pattern pattern like "yyyy-MM-dd HH:mm:ss" or "dd/MM/yyyy HH:mm:ss" or "dd/MM/yyyy" e.g
     * @return DateUtil
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

    public static DateUtil of(long timestamp) {
        return new DateUtil(timestamp);
    }

    /**
     * DateUtil.formatter(Sting pattern)
     *
     * @param pattern pattern like "yyyy-MM-dd HH:mm:ss" or "dd/MM/yyyy HH:mm:ss" or "dd/MM/yyyy" e.g
     * @return String
     */
    public String formatter(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * DateUtil.formatter(FormatStyle formatStyle)
     *
     * @param formatStyle FULL/LONG/MEDIUM/SHORT
     * @return String
     */
    public String formatter(FormatStyle formatStyle) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(formatStyle);
        return localDateTime.format(formatter);
    }
}
