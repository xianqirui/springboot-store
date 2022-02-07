package com.xqr.stroe.service.exception;

public class CartNotFindException extends ServiceException{
    public CartNotFindException() {
    }

    public CartNotFindException(String message) {
        super(message);
    }

    public CartNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFindException(Throwable cause) {
        super(cause);
    }

    public CartNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
