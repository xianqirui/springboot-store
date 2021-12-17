package com.xqr.stroe.service.exception;
/*用户名被占用异常*/
public class UserNameException extends ServiceException{

    public UserNameException() {
        super();
    }

    public UserNameException(String message) {
        super(message);
    }

    public UserNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameException(Throwable cause) {
        super(cause);
    }

    protected UserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
