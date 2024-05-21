package ru.yandex.practicum.catsgram.utils;

import java.sql.Date;
import java.time.Instant;

public class DateUtils {
    public static Instant toInstant(Date date) {
        if (date == null) {
            return null;
        }
        return Instant.ofEpochMilli(date.getTime());
    }
}
