package com.junker.dubbomock.passport.vo.input;

public class UserInfoByTokenInputBean {
    public String apiUserId;
    public String mac;
    public String channel;
    public String accessToken;
    public UserInfoByTokenInputBean() {
    }
    public void setApiUserId(String apiUserId) {
        this.apiUserId = apiUserId;
    }

    public String getApiUserId() {
        return apiUserId;
    }

    public String getMac() {
        return mac;
    }

    public String getChannel() {
        return channel;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
