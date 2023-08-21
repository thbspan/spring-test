package com.test.mvc.argument.resolver;

public class ArgDemo {
    private final String name;
    private final int age;

    public ArgDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "ArgDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
