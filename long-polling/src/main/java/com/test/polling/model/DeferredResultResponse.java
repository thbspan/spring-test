package com.test.polling.model;

public class DeferredResultResponse {
    private int code;
    private String msg;

    public enum Msg {
        TIMEOUT(408, "超时"),
        FAILED(500, "失败"),
        SUCCESS(200, "成功");

        private final int code;
        private final String desc;

        Msg(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
