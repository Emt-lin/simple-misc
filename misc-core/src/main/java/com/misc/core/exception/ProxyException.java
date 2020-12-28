package com.misc.core.exception;


/**
 * 代理异常
 *
 */
public final class ProxyException extends HandlerException {

    public ProxyException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public ProxyException() {
    }

    public ProxyException(Throwable cause) {
        super(cause);
    }
}
