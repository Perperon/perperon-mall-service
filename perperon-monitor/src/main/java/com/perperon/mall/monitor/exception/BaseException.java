package com.perperon.mall.monitor.exception;

/**
 * @author perperon
 * @date 2024/5/19
 * @apiNote
 */
public class BaseException extends RuntimeException {
    private Object code;

    public BaseException() {
    }

    public BaseException(Throwable cause, Object code, Object... params) {
        super(String.format(ExceptionCache.getProperty(code), params), cause);
        this.code = code;
    }

    public BaseException(Object code, Object... params) {
        super(String.format(ExceptionCache.getProperty(code), params));
        this.code = code;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public Object getCode() {
        return this.code;
    }

    public void setCode(Object code) {
        this.code = code;
    }
}
