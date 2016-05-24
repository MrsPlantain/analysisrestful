package org.oasis.exception;

/**
 * ���Ҳ���setting�е�urlʱ�׳����쳣
 * Created by chao on 2016/5/6.
 */
public class URLCanNotFoundException extends Exception {
    public URLCanNotFoundException() {
        super();
    }

    public URLCanNotFoundException(String message) {
        super(message);
    }

    public URLCanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public URLCanNotFoundException(Throwable cause) {
        super(cause);
    }

    protected URLCanNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
