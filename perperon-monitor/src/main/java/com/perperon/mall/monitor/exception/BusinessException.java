package com.perperon.mall.monitor.exception;

import org.apache.log4j.Logger;

/**
 * @author perperon
 * @date 2024/5/19
 * @apiNote
 */
public class BusinessException extends BaseException {
    private static final Logger logger = Logger.getLogger(BusinessException.class.getName());

    public BusinessException() {
    }

    public BusinessException(Object code, Object... params) {
        super(code, params);
    }

    public BusinessException(Throwable cause, Object code, Object... params) {
        super(cause, code, params);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessException(String message, Throwable cause) {
        super(message);
        logger.error("异常详情:" + cause.getMessage());
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
