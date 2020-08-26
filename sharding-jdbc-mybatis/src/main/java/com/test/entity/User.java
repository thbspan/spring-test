package com.test.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 7496119378521092880L;
    private int userId;

    private String userName;

    private String pwd;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return String.format("user_id: %d, user_name: %s, pwd: %s", userId, userName, pwd);
    }
}
