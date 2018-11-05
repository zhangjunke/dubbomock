package com.junker.dubbomock.passport.vo.output;

import java.io.Serializable;

public class UserInfoApiOut implements Serializable {
    private String code;
    private String msg;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
