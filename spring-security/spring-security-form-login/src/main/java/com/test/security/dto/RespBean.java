package com.test.security.dto;

public class RespBean {
    private String msg;

    public static RespBean ok(String s) {
        RespBean respBean = new RespBean();
        respBean.setMsg(s);
        return respBean;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static RespBean error(String message) {
        RespBean respBean = new RespBean();
        respBean.setMsg(message);
        return respBean;
    }
}
