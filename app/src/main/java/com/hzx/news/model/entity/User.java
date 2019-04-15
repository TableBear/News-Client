package com.hzx.news.model.entity;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/15 19:50
 * @param:
 * @return:
 * @throws:
 */
public class User {

    /**
     * uid : 4
     * unick : 暴君熊
     * phone : null
     * qq : null
     * email : 13602291948@163.com
     * password : 123456
     * status : 0
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiNCIsIuaatOWQm-eGiiJdfQ.j-IDclGR2yk_PUQiSK_UEAfm1taZ_snCDcxBchx1gME
     */

    private int uid;
    private String unick;
    private String phone;
    private String qq;
    private String email;
    private String password;
    private int status;
    private String token;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUnick() {
        return unick;
    }

    public void setUnick(String unick) {
        this.unick = unick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
