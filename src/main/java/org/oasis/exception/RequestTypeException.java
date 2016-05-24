package org.oasis.exception;

/**
 * «Î«Û∑Ω∑®¥ÌŒÛ
 * Created by chao on 2016/5/6.
 */
public class RequestTypeException extends RuntimeException {
    public RequestTypeException() {
        super();
    }

    public RequestTypeException(String message) {
        super(message);
    }

    public RequestTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestTypeException(Throwable cause) {
        super(cause);
    }

    protected RequestTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
