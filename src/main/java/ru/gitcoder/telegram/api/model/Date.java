package ru.gitcoder.telegram.api.model;

import lombok.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public final class Date {
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault());
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
    private static final DateTimeFormatter TIME_12H_FORMATTER =
            DateTimeFormatter.ofPattern("hh:mm:ss a").withZone(ZoneId.systemDefault());
    private static final DateTimeFormatter DATE_TIME_12H_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a").withZone(ZoneId.systemDefault());

    private long timestamp;

    public String getFormatted(String pattern) {
        return DateTimeFormatter.ofPattern(pattern)
                .withZone(ZoneId.systemDefault())
                .format(Instant.ofEpochSecond(timestamp));
    }

    public String getFormatted(DateTimeFormatter formatter) {
        return formatter.format(Instant.ofEpochSecond(timestamp));
    }

    public String getTime() {
        return TIME_FORMATTER.format(Instant.ofEpochSecond(timestamp));
    }

    public String getAsDefault() {
        return DATE_FORMATTER.format(Instant.ofEpochSecond(timestamp));
    }

    public String getDateTime() {
        return DATE_TIME_FORMATTER.format(Instant.ofEpochSecond(timestamp));
    }

    public String getTime12Hour() {
        return TIME_12H_FORMATTER.format(Instant.ofEpochSecond(timestamp));
    }

    public String getDateTime12Hour() {
        return DATE_TIME_12H_FORMATTER.format(Instant.ofEpochSecond(timestamp));
    }
}