package com.test.prop.entity;

public class Authorization {
    private String apiKey;
    private String token;

    public Authorization() {
    }

    public Authorization(String apiKey, String token) {
        this.apiKey = apiKey;
        this.token = token;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "apiKey='" + apiKey + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
