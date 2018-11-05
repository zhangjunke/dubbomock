package com.junker.dubbomock.passport.data;

public class UserInfoByTokenData {
    public static String code="";
    public static String msg="";
    public static String token="";
    public static String userId="";
    public static String channel="";
    public static String mac="";
    public static String timeout ="";

    public static String getTimeout() {
        return timeout;
    }

    public static void setTimeout(String timeout) {
        UserInfoByTokenData.timeout = timeout;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

}
