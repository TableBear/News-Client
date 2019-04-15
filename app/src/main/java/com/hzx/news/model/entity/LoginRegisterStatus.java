package com.hzx.news.model.entity;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 23:49
 * @param:
 * @return:
 * @throws:
 */
public class LoginRegisterStatus {
    private String code;
    private String info;
    private String token;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

