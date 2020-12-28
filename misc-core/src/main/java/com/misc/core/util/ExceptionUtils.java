package com.misc.core.util;

/**
 * todo
 */
public class ExceptionUtils {

    public static RuntimeException newRuntimeException(String format, Object ...args) {
        return new RuntimeException(String.format(format, args));
    }

    public static RuntimeException newNullPointerException(String format, Object ...args) {
        return new NullPointerException(String.format(format, args));
    }
}
