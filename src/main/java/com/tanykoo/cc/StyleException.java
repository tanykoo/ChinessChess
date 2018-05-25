package com.tanykoo.cc;

/**
 * @Author ThinkPad
 * Created : 2018-05-25 12:44
 * @Since
 */
public class StyleException extends RuntimeException {

    public StyleException() {
        super();
    }


    public StyleException(String message) {
        super(message);
    }


    public StyleException(String message, Throwable cause) {
        super(message, cause);
    }


    public StyleException(Throwable cause) {
        super(cause);
    }


    protected StyleException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
