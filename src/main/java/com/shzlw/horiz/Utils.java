package com.shzlw.horiz;

import java.util.concurrent.TimeUnit;

public final class Utils {

    private Utils() {
    }

    public static long getOrDefault(long val, long defaultVal) {
        return val == 0 ? defaultVal : val;
    }

    public static TimeUnit getOrDefault(TimeUnit val, TimeUnit defaultVal) {
        return val == null ? defaultVal : val;
    }

    public static String notEmpty(String s, String message) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException(message);
        }

        return s;
    }
}
