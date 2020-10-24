package com.test.annotation.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            System.out.println("开始执行");
            return invocation.proceed();
        } finally {
            System.out.println("执行完毕");
        }
    }
}
