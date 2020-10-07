package com.test.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspects {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspects.class);

    public LogAspects() {
        LOGGER.info("=================== init log aspects");
    }

    @Pointcut("execution(* com.test.annotation.service.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            LOGGER.info("=================== logAround  开始执行:" + joinPoint.getSignature().getName());
            return joinPoint.proceed();
        } finally {
            LOGGER.info("=================== logAround  结束执行:" + joinPoint.getSignature().getName());
        }

    }

    /**
     * 在目标方法执行前
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        LOGGER.info("=================== logStart  开始执行:" + joinPoint.getSignature().getName());
    }

    /**
     * 在目标方法执行后
     */
    @After("pointCut()")
    public void logAfter() {
        LOGGER.info("=================== logAfter  结束执行");
    }

    /**
     * 方法返回后
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturning(Object result) {
        LOGGER.info("=================== logReturning  正常返回，结果" + result);
    }

    /**
     * 出现异常后
     */
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        LOGGER.info("=================== logStarException  运行异常:" + exception);
    }
}
