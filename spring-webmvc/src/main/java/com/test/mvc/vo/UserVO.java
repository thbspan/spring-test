package com.test.mvc.vo;

import com.alibaba.excel.EasyExcel;

import java.util.HashMap;

public class UserVO {
    private int id;
    private String userName;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        EasyExcel.write("df").withTemplate("fdd").sheet().doFill(new HashMap<>());

        return "UserVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
