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

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public Token(String token, String nick) {
        this.token = token;
        this.nick = nick;
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
}
