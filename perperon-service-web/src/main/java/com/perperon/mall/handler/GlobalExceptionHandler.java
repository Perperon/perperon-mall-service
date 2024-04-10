package com.perperon.mall.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dupengcheng
 * @date 2024-04-10
 * @apiNote
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception ex) {
        // 这里处理异常，并返回一个合适的响应
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
