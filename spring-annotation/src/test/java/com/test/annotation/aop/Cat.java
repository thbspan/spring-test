package com.test.annotation.aop;

public class Cat implements Animal{

    @Override
    public void bark() {
        System.out.println("miao miao miao...");
    }
}
