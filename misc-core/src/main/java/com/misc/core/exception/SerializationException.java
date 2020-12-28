package com.misc.core.exception;

/**
 * 序列化异常
 *
 */
public class SerializationException extends RuntimeException {

    private static final long serialVersionUID = -2734233562552761766L;


    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationException(Throwable cause) {
        super(cause);
    }
}
