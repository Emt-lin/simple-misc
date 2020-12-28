package com.misc.core.exception;

/**
 * 超时异常
 *
 */
public final class TimeOutException extends RuntimeException {

    private static final long serialVersionUID = 5411313278598888161L;

    public TimeOutException(String message) {
        super(message);
    }


    public TimeOutException(Throwable cause) {
        super(cause);
    }
}
