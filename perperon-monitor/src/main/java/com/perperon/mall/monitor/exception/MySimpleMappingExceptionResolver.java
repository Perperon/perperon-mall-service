package com.perperon.mall.monitor.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author perperon
 * @date 2024/5/19
 * @apiNote 统一异常处理
 */
@ControllerAdvice
@Order(1)
public class MySimpleMappingExceptionResolver implements HandlerExceptionResolver {
    private static ObjectMapper jsonMapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(MySimpleMappingExceptionResolver.class);

    public MySimpleMappingExceptionResolver() {
    }

    @ExceptionHandler
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            OutputStream writer = response.getOutputStream();
            Map<String, Object> map = new HashMap();
            map.put("status", -1);
            map.put("success", false);
            String msg = exception.getMessage();
            if (exception instanceof BusinessException) {
                response.setHeader("Content-type", "application/json;charset=UTF-8");
                map.put("code", "业务异常");
            } else {
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                map.put("code", "系统异常");
                if (exception.getClass() != null) {
                    msg = exception.getClass().getName();
                }

                if (exception.getStackTrace() != null && exception.getStackTrace().length > 0) {
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement statck = exception.getStackTrace()[0];
                    sb.append("文件:");
                    sb.append(statck.getFileName());
                    sb.append(",类名:");
                    sb.append(statck.getClassName());
                    sb.append(",方法:");
                    sb.append(statck.getMethodName());
                    sb.append(",行数:");
                    sb.append(statck.getLineNumber());
                    sb.append("错误信息为:");
                    sb.append(msg);
                    msg = sb.toString();
                }
            }

            map.put("message", msg);
            logger.info(msg, exception);
            writer.write(jsonMapper.writeValueAsString(map).getBytes("UTF-8"));
            writer.flush();
            writer.close();
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return null;
    }
}

