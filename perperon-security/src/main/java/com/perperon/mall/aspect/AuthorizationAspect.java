package com.perperon.mall.aspect;

import cn.hutool.extra.spring.SpringUtil;
import com.perperon.mall.annotation.PreAuthorize;
import com.perperon.mall.common.response.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author dupengcheng
 * @date 2024-07-03
 * @apiNote
 */
@Component
@Aspect
public class AuthorizationAspect {

    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    @Pointcut("@annotation(com.perperon.mall.annotation.PreAuthorize)")
    public void pt() {
    }

    @Around("pt()")
    public CommonResult checkAuthority(ProceedingJoinPoint joinPoint) {
        //拿到方法的签名，也可以理解为是方法的元数据
        try {
            // 获取方法签名
            MethodSignature ms = joinPoint.getSignature() instanceof MethodSignature ? ((MethodSignature) joinPoint.getSignature()) : null;
            // 从方法签名中获取方法对象
            Method method = ms.getMethod();
            // 获取方法上的 @PreAuthorize 注解
            PreAuthorize annotation = method.getAnnotation(PreAuthorize.class);
            // 获取注解的值，即权限验证的条件
            String condition = annotation.value();
            // 使用 SpEL 表达式解析器解析权限验证条件
            Expression expression = EXPRESSION_PARSER.parseExpression(condition);
            // 获取 Spring 容器中的 PermissionService Bean，并执行权限验证条件
            if (Boolean.TRUE.equals(expression.getValue(SpringUtil.getBean(AuthorityPerms.class), Boolean.class))) {
                // 权限验证通过，继续执行连接点方法
                return (CommonResult) joinPoint.proceed();
            } else {
                // 权限验证失败，返回错误结果
                return CommonResult.failed("越权操作！！！");
            }
        } catch (Throwable e) {
            // 捕获异常并记录日志
            e.printStackTrace();
            // 返回未知异常信息
            return CommonResult.failed("未知异常，请联系管理员查看日志");
        }
    }
}
