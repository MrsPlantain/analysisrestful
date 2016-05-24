package org.oasis.exception;

/**
 * URL格式不正确
 * Created by chao on 2016/5/6.
 */
public class URLFormatException extends Exception{
    public URLFormatException() {
        super();
    }

    public URLFormatException(String message) {
        super(message);
    }

    public URLFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public URLFormatException(Throwable cause) {
        super(cause);
    }

    protected URLFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
