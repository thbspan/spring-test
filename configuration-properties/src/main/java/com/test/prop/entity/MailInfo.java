package com.test.prop.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mail")
public class MailInfo {
    /**
     * mail host name
     */
    private String hostName;
    private int port;
    private String from;
    private String to;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "MailInfo{" +
                "hostName='" + hostName + '\'' +
                ", port=" + port +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
