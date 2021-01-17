package com.test.annotation.loopup;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClassB {
    public void printClass() {
        System.out.println("This is Class B: " + this);
    }
}
