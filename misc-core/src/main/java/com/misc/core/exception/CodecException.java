package com.misc.core.exception;

/**
 * 编解码异常
 *
 */
public class CodecException extends RuntimeException {

    private static final long serialVersionUID = 6495753178431655677L;


    public CodecException() {

    }

    public CodecException(Throwable cause) {
        super(cause);
    }

    public CodecException(String message) {
        super(message);
    }
}
