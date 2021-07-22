package com.test.mvc.vo;

import org.springframework.hateoas.RepresentationModel;

public class UserEntityModel extends RepresentationModel<UserEntityModel> {
    private int id;
    private String userName;
    private int age;

    public UserEntityModel(UserVO userVO) {
        this.id = userVO.getId();
        this.userName = userVO.getUserName();
        this.age = userVO.getAge();
    }

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
}
