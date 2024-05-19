package com.perperon.mall.monitor.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author perperon
 * @date 2024/5/19
 * @apiNote
 */
@Aspect
@Component
public class LogAfterAdvice {
    private static Logger logger = LoggerFactory.getLogger(LogAfterAdvice.class);
    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public LogAfterAdvice() {
    }

    @Pointcut("execution(* com.perperon.mall.service.*.*(..))")
    public void show() {
    }

    @Before("show()")
    public void Before(JoinPoint jp) {
        try {
            String ss = jsonMapper.writeValueAsString(jp.getArgs());
            logger.info("调用方法前[" + jp.toLongString() + "] 传入相关数据：" + ss);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    @AfterThrowing(
            value = "show()",
            throwing = "e1"
    )
    public void afterThrowing(JoinPoint jp, Exception e1) {
        try {
            String ss = jsonMapper.writeValueAsString(jp.getArgs());
            logger.info("(错误)调用方法后[" + jp.toLongString() + "] 返回相关数据：" + ss);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    @AfterReturning(
            value = "show()",
            returning = "obj"
    )
    public Object afterReturning(JoinPoint jp, Object obj) {
        try {
            String ss = jsonMapper.writeValueAsString(obj);
            logger.info("调用方法后[" + jp.toLongString() + "] 返回相关数据：" + ss);
        } catch (Exception var4) {
            var4.printStackTrace();
            logger.info("(错误)LogAfter:" + var4.getMessage());
        }

        return null;
    }
}

