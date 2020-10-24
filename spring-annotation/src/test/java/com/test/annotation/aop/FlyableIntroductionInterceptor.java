package com.test.annotation.aop;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class FlyableIntroductionInterceptor extends DelegatingIntroductionInterceptor implements Flyable{
    @Override
    public void fly() {
        System.out.println("fly fly fly...");
    }
}
