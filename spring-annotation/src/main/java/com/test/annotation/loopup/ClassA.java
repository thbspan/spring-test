package com.test.annotation.loopup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ClassA {

    private Another another;

    public void printClass() {
        System.out.println("This is Class A: " + this);
        getClassB().printClass();
        another.printClass();
    }

    @Lookup
    public ClassB getClassB() {
        return null;
    }

    @Autowired
    public void setAnother(Another another) {
        this.another = another;
    }
}
