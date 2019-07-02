package com.hzx.news.model.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 13:57
 */
public class Token extends LitePalSupport {
    private String token;
    private String nick;
    private long uid;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public Token(String token, String nick, long uid) {
        this.token = token;
        this.nick = nick;
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
