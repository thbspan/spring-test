package com.test.entity;

import java.io.Serializable;

public class ShadowUser implements Serializable {

    private static final long serialVersionUID = 1072653371465080185L;
    private int userId;

    private String userName;

    private String userNamePlain;

    private String pwd;

    private String assistedQueryPwd;

    private boolean shadow;

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

    public String getUserNamePlain() {
        return userNamePlain;
    }

    public void setUserNamePlain(String userNamePlain) {
        this.userNamePlain = userNamePlain;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAssistedQueryPwd() {
        return assistedQueryPwd;
    }

    public void setAssistedQueryPwd(String assistedQueryPwd) {
        this.assistedQueryPwd = assistedQueryPwd;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    @Override
    public String toString() {
        return String.format("user_id: %d, user_name: %s, user_name_plain: %s, pwd: %s, assisted_query_pwd: %s, shadow: %s", userId, userName, userNamePlain, pwd, assistedQueryPwd, shadow);
    }
}
