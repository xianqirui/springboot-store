package com.xqr.stroe.service.exception;
/*收获地址总数超出限制（20条）*/
public class AdressCountLimtException extends ServiceException{
    public AdressCountLimtException() {
    }

    public AdressCountLimtException(String message) {
        super(message);
    }

    public AdressCountLimtException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdressCountLimtException(Throwable cause) {
        super(cause);
    }

    public AdressCountLimtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
