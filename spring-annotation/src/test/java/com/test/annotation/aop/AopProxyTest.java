package com.test.annotation.aop;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.framework.ProxyFactory;

public class AopProxyTest {

    @Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new LogInterceptor());
        proxyFactory.addAdvice(new TimeInterceptor());
        proxyFactory.addAdvice(new AopMethodBeforeAdvice());
        proxyFactory.addAdvice(new FlyableIntroductionInterceptor());
        proxyFactory.setTarget(new Cat());
        for (Class<?> ifc : Cat.class.getInterfaces()) {
            proxyFactory.addInterface(ifc);
        }
        proxyFactory.setProxyTargetClass(false);
        Object proxy = proxyFactory.getProxy();

        if (proxy instanceof Animal) {
            ((Animal) proxy).bark();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (proxy instanceof Flyable) {
            ((Flyable) proxy).fly();
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if (proxy instanceof IntroductionInfo) {
            Arrays.stream(((IntroductionInfo) proxy).getInterfaces()).forEach(System.out::println);
        }
    }
}
