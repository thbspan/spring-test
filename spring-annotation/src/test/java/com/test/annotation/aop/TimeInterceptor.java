package com.test.annotation.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("计时开始");
        try {
            return invocation.proceed();
        } finally {
            System.out.println("计时结束，耗时" + (System.currentTimeMillis() - start) / 1000);
        }

    }
}
